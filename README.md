### Data Validator:

[![Actions Status](https://github.com/IVF13/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/IVF13/java-project-lvl3/actions)
[![Build workflow](https://github.com/IVF13/java-project-lvl3/actions/workflows/build.yml/badge.svg)](https://github.com/IVF13/java-project-lvl3/actions/workflows/build.yml)
<a href="https://codeclimate.com/github/IVF13/java-project-lvl3/maintainability"><img src="https://api.codeclimate.com/v1/badges/450c76b84170c15ee7f3/maintainability" /></a>
<a href="https://codeclimate.com/github/IVF13/java-project-lvl3/test_coverage"><img src="https://api.codeclimate.com/v1/badges/450c76b84170c15ee7f3/test_coverage" /></a>

Data Validator - library that helps to check data correctness, and it's compliance with established criteria. The
library can work with different data types. Using the tools of this library, it is easy to check the Integer, String and
Map data types. It can be integrated into a service that accepts user input. The yup library was used as a reference for
this one.

#### Functional:

```java
//Imports:

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;

public void toCheckString(){
        Validator validator=new Validator();
        StringSchema stringSchema=validator.string();


        stringSchema.isValid(""); //true

        stringSchema.required(); //adds a check if the input contains an instance of a non-empty string   


        stringSchema.isValid(5); //false
        stringSchema.isValid(""); //false
        stringSchema.isValid(null); //false
        stringSchema.isValid(new ArrayList<>()); //false
        stringSchema.isValid("Hexlet"); //true


        stringSchema.minLength(4); //adds a check if input contains a string with a 
        //length equal to or greater than the specified
        stringSchema.isValid("whatthe"); //true
        stringSchema.isValid("the"); //false


        stringSchema.contains("what"); //adds a check if input contains a string with specified string

        stringSchema.isValid("what does the fox say"); //true
        stringSchema.isValid("wha does the fox say"); //false

        //You can combine checks in any order eg("isValid" always is terminal check): 
        stringSchema.required().minLength(5).contains("the").isValid("what does the fox say");
        }

public void toCheckNumber(){
        Validator validator=new Validator();
        NumberSchema numberSchema=validator.number();


        numberSchema.isValid("lol"); //true

        numberSchema.required(); //adds a check if the input contains an instance of Integer

        numberSchema.isValid("lol"); //false
        stringSchema.isValid(null); //false
        stringSchema.isValid(new ArrayList<>()); //false
        numberSchema.isValid(5); //true
        numberSchema.isValid(-1); //true
        numberSchema.isValid(0); //true


        numberSchema.positive() //adds a check if the input contains Integer bigger than 0

        numberSchema.isValid(-10); //false
        numberSchema.isValid(10); //true


        numberSchema.range(5,10); //adds a check if the input contains Integer from the specified range

        numberSchema.isValid(4); //false
        numberSchema.isValid(14); //false
        numberSchema.isValid(5); //true
        numberSchema.isValid(7); //true

        //You can combine checks in any order eg("isValid" always is terminal check): 
        numberSchema.required().positive().range(5,10).isValid(6);
        }

public void toCheckMap(){
        Validator validator=new Validator();
        MapSchema mapSchema=validator.map();


        mapSchema.isValid(""); //true

        mapSchema.required(); //adds a check if the input contains an instance of Map

        mapSchema.isValid(""); //false
        mapSchema.isValid(new HashMap<>()); //true


        mapSchema.sizeof(2); //adds a check if the input contains Map with specified size

        Map<String, String> data=new HashMap<>();
        data.put("key1","value1");

        mapSchema.isValid(data); //false

        data.put("key2","value2");

        mapSchema.isValid(data); //true


        Map<String, BaseSchema> schemas=new HashMap<>();
        schemas.put("name",validator.string().required());
        schemas.put("age",validator.number().positive());

        mapSchema.shape(schemas); //adds a check if the input contains Map with valid values in relation to certain keys, 
        // the way to validate must be defined in Map with same keys

        Map<String, Object> human1=new HashMap<>();
        human1.put("name","Kolya");
        human1.put("age",100);
        mapSchema.isValid(human1); //true

        Map<String, Object> human2=new HashMap<>();
        human2.put("name","");
        human2.put("age",null);
        mapSchema.isValid(human2); //false

        Map<String, Object> human3=new HashMap<>();
        human3.put("name","Valya");
        human3.put("age",-5);
        mapSchema.isValid(human3); //false

        //You can combine checks in any order eg("isValid" always is terminal check): 
        mapSchema.required().sizeof(2).shape(schemas).isValid(human1);
        }
```

        
        


