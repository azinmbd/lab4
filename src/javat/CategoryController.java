package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequestMapping
@Controller
public class CategoryController {
    @Autowired
    Catdao dao;

    @GetMapping(path = "/category")
    public  String showcategorypage(ModelMap model) throws ClassNotFoundException, SQLException{
        List<Category> List = dao.display();
        model.addAttribute("categorylist", List);
        model.put("id", List.get(0).getCatcode());
        model.put("desc" , List.get(0).getCatdesc());
        return "category";
    }
    @GetMapping(path = "/")
    public  String showcategorypage2(ModelMap model) throws ClassNotFoundException, SQLException{
        List<Category> List = dao.display();
        model.addAttribute("categorylist", List);
        model.put("id", List.get(0).getCatcode());
        model.put("desc" , List.get(0).getCatdesc());
        return "category";
    }

    @GetMapping(path="/add-todo")
    public String showtodopage(){
        return "catser";
    }
        @PostMapping(path="/add-todo")
        public String addTodo (ModelMap model, @RequestParam String catcode, @RequestParam String catdesc) throws SQLException, ClassNotFoundException {
            List<Map<String, Object>> x = dao.getcat(catcode);
            String iidi, ccdesc1;
            x.forEach(rowMap ->{
            String iid = (String) rowMap.get("catcode");
            model.put("id",iid);
            String ccdesc = (String) rowMap.get("catdesc");
            model.put("desc", ccdesc);
            });
            if(x.isEmpty()==false){
                model.put("errorMessage", "Record Existing");
                return "redirect:/category";
            }
        Category cc = new Category();
        cc.setCatcode(catcode);
        cc.setCatdesc(catdesc);
        dao.insertData(cc);
        model.addAttribute(  "cc", cc);
        return "redirect:/category";
        }

}
