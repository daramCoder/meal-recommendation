package toy.ojm.client.dto;

import com.mysql.cj.xdevapi.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import toy.ojm.entity.RestaurantEntity;
import toy.ojm.repository.RestaurantRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelToDatabaseService {

    private final RestaurantRepository restaurantRepository;

    public ExcelToDatabaseService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void saveDataToDatabase(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            List<RestaurantEntity> restaurantEntities = new ArrayList<>();

            for (org.apache.poi.ss.usermodel.Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                RestaurantEntity entity = createRestaurantEntity((Row) row);
                if (entity != null) {
                    restaurantEntities.add(entity);
                }
            }

            restaurantRepository.saveAll(restaurantEntities);

        } catch (IOException e) {
            e.printStackTrace();
            // 적절한 예외 처리 로직 추가
        }
    }

    private RestaurantEntity createRestaurantEntity(Row row) {
        try {
            RestaurantEntity entity = new RestaurantEntity();
            entity.setDTLSTATENM(row.getCell(0).getStringCellValue());
            entity.setSITEWHLADDR(row.getCell(1).getStringCellValue());
            entity.setRDNWHLADDR(row.getCell(2).getStringCellValue());
            entity.setBPLCNM(row.getCell(3).getStringCellValue());
            entity.setUPTAENM(row.getCell(4).getStringCellValue());
            entity.setX(row.getCell(5).getStringCellValue());
            entity.setY(row.getCell(6).getStringCellValue());
            return entity;
        } catch (Exception e) {
            // 처리되지 않은 예외 발생 시 로그 출력
            e.printStackTrace();
            return null;
        }
    }
}