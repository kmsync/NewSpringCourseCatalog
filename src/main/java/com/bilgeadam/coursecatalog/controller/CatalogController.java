package com.bilgeadam.coursecatalog.controller;

import com.bilgeadam.coursecatalog.pojo.Course;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private EurekaClient client;

    @GetMapping("/hello-course-without-eureka")
    public String helloCourseWithoutEureka()
    {
        String courseAppMessage = "";
        String courseAppURL = "http://localhost:6161/api/v1";

        RestTemplate restTemplate = new RestTemplate();
        courseAppMessage = restTemplate.getForObject(courseAppURL, String.class);

        return "Course'dan gelen mesaj: " +courseAppMessage;
    }

    @GetMapping("/course-list-without-eureka")
    public String courseListWithoutEureka()
    {
        String courseAppMessage = "";
        String courseAppURL = "http://localhost:6161/api/v1/course";

        RestTemplate restTemplate = new RestTemplate();
        courseAppMessage = restTemplate.getForObject(courseAppURL, String.class);

        return "Course List: " + courseAppMessage;
    }

    @GetMapping("/hello-course-with-eureka")
    public String helloCourseWithEureka()
    {
        String courseAppMessage = "";
        //String courseAppURL = "http://localhost:6161/api/v1";
        String courseAppURL = "";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-app",false);
        courseAppURL = instanceInfo.getHomePageUrl();

        courseAppMessage = restTemplate.getForObject(courseAppURL + "/api/v1", String.class);

        return "Course'dan gelen mesaj: " +courseAppMessage;
    }

    @GetMapping("/course-list")
    public List<Course> courseList()
    {
        String courseAppURL = "";

        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-app",false);
        courseAppURL = instanceInfo.getHomePageUrl();

        List<Course> courseList = restTemplate.getForObject(courseAppURL + "/api/v1/course", List.class);

        return courseList;
    }

    @GetMapping("/first-course")
    public Course firstCourse()
    {
        String courseAppURL = "";

        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-app",false);
        courseAppURL = instanceInfo.getHomePageUrl();

        Course firstCourse = restTemplate.getForObject(courseAppURL + "/api/v1/course/1", Course.class);

        return firstCourse;
    }
}