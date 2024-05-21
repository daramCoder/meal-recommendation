package toy.ojm.domain.location;

import lombok.extern.slf4j.Slf4j;
import org.locationtech.proj4j.BasicCoordinateTransform;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TransCoordination {

    public ProjCoordinate transformToGCS(
        Double longitude,
        Double latitude
    ){  // GCS 좌표계로의 변환을 수행하는 메서드
        // CRS 객체 생성
        CRSFactory factory = new CRSFactory();  // CoordinateReferenceSystem을 생성하기 위한 CRSFactory 인스턴스 생성

        // WGS84 system 정의
        String wgs84Name = "WGS84";
        String wgs84Proj = "+proj=longlat +datum=WGS84 +no_defs";
        CoordinateReferenceSystem wgs84System = factory.createFromParameters(wgs84Name, wgs84Proj);

        // UTMK system 정의
        String utmkName = "UTMK";
        String utmkProj = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";
        CoordinateReferenceSystem utmkSystem = factory.createFromParameters(utmkName, utmkProj);

        // 변환할 좌표계 정보 생성
        ProjCoordinate p = new ProjCoordinate();
        p.x = longitude;
        p.y = latitude;

        // 변환된 좌표를 담을 객체 생성
        ProjCoordinate q = new ProjCoordinate();

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        // 변환 시스템 지정. (원본 시스템, 변환 시스템)
        CoordinateTransform coordinateTransform = ctFactory.createTransform(wgs84System, utmkSystem);

        // 좌표 변환
        ProjCoordinate projCoordinate = coordinateTransform.transform(p, q);

        // 변환된 좌표
        double x = projCoordinate.x;
        double y = projCoordinate.y;

        log.info("변환된 x 좌표 : " + x);
        log.info("변환된 y 좌표 : " + y);
        return projCoordinate;
    }
}
