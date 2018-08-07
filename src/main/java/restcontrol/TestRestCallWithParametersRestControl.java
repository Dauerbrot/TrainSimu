package restcontrol;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestRestCallWithParametersRestControl {

    @RequestMapping("/test")
    String getSomeStationNames(@RequestParam Map<String,String> costumQueries){
        StringBuilder message = new StringBuilder();

        for(Map.Entry<String,String> costumQuery: costumQueries.entrySet()){
            message.append("Key:").append(costumQuery.getKey()).append(" Value:").append(costumQuery.getValue()).append(System.getProperty("line.separator"));
            System.out.println(message);
        }
        if(message.length() == 0){
            return "no informations was given over parameters. add to the url '?rest=true&nest=muh' as example";
        }
        return message.toString();
    }
}
