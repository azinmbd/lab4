package javat;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Catdao {
    public Catdao(JdbcTemplate template){
        this.template = template;
    }
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public List<Category> display() throws ClassNotFoundException, SQLException{
        return  template.query("select * from category" , (RowMapper) (rs, row) ->{
           Category c = new Category();
           c.setCatcode(rs.getString(1));
           c.setCatdesc(rs.getString(2));
           return c;
        });
    }

    public int insertData( final Category category) {
        return template.update("insert into category values(?,?)", category.getCatcode(), category.getCatdesc());
    }
    public int deleteData (String cat){
        return template.update("delete from category where catcode = ?", cat);
    }
    public int EditData(final Category category, String cat){
        return  template.update("update  category set  catcode=? , catdesc = ? where catcode=?", category.getCatcode(), category.getCatdesc(), cat);
    }
    public  List<Map<String, Object>> getcat (String cat){
        return  template.queryForList("SELECT * from category where catcode = ?", cat);
    }
}
