package ir.aamnapm.druid.service;

import ir.aamnapm.druid.config.Config;
import ir.aamnapm.druid.model.DruidData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DruidJDBCService {

    @Autowired
    private Config config;

    public List<DruidData> start() {
        try (Statement statement = config.connection().createStatement()) {

            final ResultSet rs = statement.executeQuery("SELECT * from your_table");
            return data(rs);

        } catch (SQLException e) {
            //throws exception
            return Collections.emptyList();
        }
    }

    public List<DruidData> data(ResultSet rs) throws SQLException {

        List<DruidData> druidDataList = new ArrayList<>();
        int index = 2;

        while (rs.next()) {

            DruidData druidData = new DruidData();

            druidData.setColumnName(rs.getMetaData().getColumnName(index));
            druidData.setValue(rs.getLong(index));

            druidDataList.add(druidData);

            index++;
        }

        index = 1;
        return druidDataList;
    }
}
