package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader("city.json"));
            String line = br.readLine();
            while (line != null) {
                // System.out.println(line);
                sb.append(line);
                line = br.readLine();
                // index += 1;
            }

            System.out.println("--------------------------------------------------");
            // System.out.println(sb.toString());
            // Convert to json
            Gson gson = new Gson();
            List<City> cities = gson.fromJson(sb.toString(), new TypeToken<List<City>>(){}.getType());
            int cityId = 0;
            for (City city : cities) {
                city.setId(cityId++);
                System.out.println("City name: " + city.getName() + " " + city.getId());
                int districtId = 0;
                for (var district : city.getDistricts()) {
                    district.setId(districtId++);
                    System.out.println("\tDistrict: " + district.getName() + " " + district.getId());
                    int wardId = 0;
                    for (var ward : district.getWards()) {
                        ward.setId(wardId++);
                        System.out.println("\t\tWard: " + ward.getName() + " " + ward.getId());
                    }
                }
                System.out.println("--------------------------------------------------------------------");
            }

            // System.out.println(cities.get(cities.size() - 1).getDistricts().size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

       
    }
}
