package ApiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;

import java.io.File;
import java.io.IOException;

public class SampleDeserialize {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //convert json to POJO object
        Student[] studentlist = mapper.readValue(new File("src/main/resources/data/studentdata.json"), Student[].class);

        for (Student student: studentlist) {
            System.out.println(student.toString());
        }




    }
}
