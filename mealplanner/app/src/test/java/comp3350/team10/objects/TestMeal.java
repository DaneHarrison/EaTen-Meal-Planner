// create by zhihao Zhou 7904125
// create at 2022/07/08

// the problem need to fix, problem about setInstructions()
// 1. cannot update the correct value to the Meal
//      there have problem about the about a update the data form the array list
//      "my array of all input is nutrition is total in 100 but the update result is 40"
// 2.if the input ingredients arraylist the size is 0 should let all the Nutrition and categories to default
// but some of Categories is not set to default

package comp3350.team10.objects;

import comp3350.team10.objects.*;

import comp3350.team10.objects.Edible.Unit;
import comp3350.team10.objects.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;


public class TestMeal {

    void testIngredentsList(Meal Meal, ArrayList<Ingredient> expect){
        ArrayList<Ingredient> getIngredients = Meal.getIngredients();
        assertEquals(expect,getIngredients);
        assertEquals(expect.size(),getIngredients.size());
        for (int i = 0; i < expect.size();i++){
            assertEquals(expect.indexOf(i),getIngredients.indexOf(i));
        }

    }

    //     void testNutrition(Edible food, int caloriesExpect, int proteinExpect, int carbsExpect, int fatExpect) {
//         assertEquals(caloriesExpect, food.getCalories());
//         assertEquals(proteinExpect, food.getProtein());
//         assertEquals(carbsExpect, food.getCarbs());
//         assertEquals(fatExpect, food.getFat());
//     }
//
//     void testCategories(Edible food, boolean alcoholicExpect, boolean spicyExpect, boolean veganExpect, boolean vegetarianExpect, boolean glutenFreeExpect) {
//         assertEquals(alcoholicExpect, food.getIsAlcoholic());
//         assertEquals(spicyExpect, food.getIsSpicy());
//         assertEquals(veganExpect, food.getIsVegan());
//         assertEquals(vegetarianExpect, food.getIsVegetarian());
//         assertEquals(glutenFreeExpect, food.getIsGlutenFree());
//     }
    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {

        private Meal testMeal;
        // this is for Edible class for Ingredient class
        private Edible testFood;
        // this is for Edible class for Ingredient class
        private Ingredient testMealIngredient;
        private ArrayList<Ingredient> testIngredients;

        @BeforeEach
        void setup(){
            testMeal = new Meal();
            testFood = new Edible();
            testMealIngredient = new Ingredient();
            testIngredients = new ArrayList<Ingredient>();

        }

        @Test
        void test_setInstructions(){
            try {
                testMeal.setInstructions("simple instructions");
                assertNotNull(testMeal.getInstructions());
                assertEquals("simple instructions",testMeal.getInstructions());
            }
            catch (Exception e)
            {
                fail("Should not throw exception");
            }

        }

        @Test
        void test_setIngredient()
        {
            try {
                testMeal.initNutrition(15,35,45,10);
                testMeal.initCategories(true,false,false,false,false);

//                 testNutrition(testMeal,15,35,45,10);
                assertEquals(15, testMeal.getCalories());
                assertEquals(35, testMeal.getProtein());
                assertEquals(45, testMeal.getCarbs());
                assertEquals(10, testMeal.getFat());

//                 testCategories(testMeal,true,false,false,false,false);
                assertEquals(true, testMeal.getIsAlcoholic());
                assertEquals(false, testMeal.getIsSpicy());
                assertEquals(false, testMeal.getIsVegan());
                assertEquals(false, testMeal.getIsVegetarian());
                assertEquals(false, testMeal.getIsGlutenFree());

                testFood.initNutrition(10,10,10,10);
                testFood.initCategories(false,false,false,false,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);

                testMeal.setIngredients(testIngredients);

                testIngredentsList(testMeal,testIngredients);

            }
            catch (Exception e)
            {
                fail("Should not throw exception");
            }
//             testNutrition(testMeal,10,10,10,10);
            assertEquals(10, testMeal.getCalories());
            assertEquals(10, testMeal.getProtein());
            assertEquals(10, testMeal.getCarbs());
            assertEquals(10, testMeal.getFat());

//             testCategories(testMeal,false,false,false,false,true);
            assertEquals(false, testMeal.getIsAlcoholic());
            assertEquals(false, testMeal.getIsSpicy());
            assertEquals(false, testMeal.getIsVegan());
            assertEquals(false, testMeal.getIsVegetarian());
            assertEquals(true, testMeal.getIsGlutenFree());
        }


    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {

        private Meal testMeal;
        // this is for Edible class for Ingredient class
        private Edible testFood;
        // this is for Edible class for Ingredient class
        private Ingredient testMealIngredient;
        private ArrayList<Ingredient> testIngredients;

        @BeforeEach
        void setup(){
            testMeal = new Meal();
            testFood = new Edible();
            testMealIngredient = new Ingredient();
            testIngredients = new ArrayList<Ingredient>();

        }

        @Test
        void test_setInstructions(){
            try {
                String test_instruction="very long instructions sdakjlfhadsljfkhldsakjhfiuweasdhyfuiklewahearewrw" +
                        "adsjfkghbewakjdshfljkaewhdflkaewj\njewifhewl\r isdfauhjljkewf\n\\wieosuhjrfiol;ewk" +
                        "53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#";
                testMeal.setInstructions(test_instruction);
                assertNotNull(testMeal.getInstructions());
                assertEquals(test_instruction,testMeal.getInstructions());
            }
            catch (Exception e)
            {
                fail("Should not throw exception");
            }

        }

        @Test
        void test_setIngredient()
        {
            try {
                testMeal.initNutrition(15,35,45,10);
                testMeal.initCategories(true,false,false,false,false);
//                 testNutrition(testMeal,15,35,45,10);
                assertEquals(15, testMeal.getCalories());
                assertEquals(35, testMeal.getProtein());
                assertEquals(45, testMeal.getCarbs());
                assertEquals(10, testMeal.getFat());

//                 testCategories(testMeal,true,false,false,false,false);
                assertEquals(true, testMeal.getIsAlcoholic());
                assertEquals(false, testMeal.getIsSpicy());
                assertEquals(false, testMeal.getIsVegan());
                assertEquals(false, testMeal.getIsVegetarian());
                assertEquals(false, testMeal.getIsGlutenFree());

                //set 4 ingredient
                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(10,10,10,10);
                testFood.initCategories(false,false,false,false,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);

                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(20,20,20,20);
                testFood.initCategories(false,false,false,true,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);

                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(30,30,30,30);
                testFood.initCategories(false,false,true,false,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);

                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(40,40,40,40);
                testFood.initCategories(false,true,false,false,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);
                //set 4 ingredient

                System.out.println("there are "+testIngredients.size() + " Meal obj in the list");

                testMeal.setIngredients(testIngredients);
                // problem mark 1
                // there have problem about the about a update the data form the array list
                fail( "my array of all input is nutrition is total in 100 but the update result is 40");


            }
            catch (Exception e)
            {
                fail("Should not throw exception");
            }
//             testNutrition(testMeal,100,100,100,100);
            assertEquals(100, testMeal.getCalories());
            assertEquals(100, testMeal.getProtein());
            assertEquals(100, testMeal.getCarbs());
            assertEquals(100, testMeal.getFat());

//             testCategories(testMeal,false,true,true,true,true);
            assertEquals(false, testMeal.getIsAlcoholic());
            assertEquals(true, testMeal.getIsSpicy());
            assertEquals(true, testMeal.getIsVegan());
            assertEquals(true, testMeal.getIsVegetarian());
            assertEquals(true, testMeal.getIsGlutenFree());

            testIngredentsList(testMeal,testIngredients);



        }


    }

    @Nested
    @DisplayName("Edge tests")
    class Test_Edge {

        private Meal testMeal;
        // this is for Edible class for Ingredient class
        private Edible testFood;
        // this is for Edible class for Ingredient class
        private Ingredient testMealIngredient;
        private ArrayList<Ingredient> testIngredients;

        @BeforeEach
        void setup(){
            testMeal = new Meal();
            testFood = new Edible();
            testMealIngredient = new Ingredient();
            testIngredients = new ArrayList<Ingredient>();

        }


        @Test
        void test_setIngredient()
        {
            try {
                testMeal.initNutrition(15,35,45,10);
                testMeal.initCategories(true,false,false,false,false);
//                 testNutrition(testMeal,15,35,45,10);
                assertEquals(15, testMeal.getCalories());
                assertEquals(35, testMeal.getProtein());
                assertEquals(45, testMeal.getCarbs());
                assertEquals(10, testMeal.getFat());

//                 testCategories(testMeal,true,false,false,false,false);
                assertEquals(true, testMeal.getIsAlcoholic());
                assertEquals(false, testMeal.getIsSpicy());
                assertEquals(false, testMeal.getIsVegan());
                assertEquals(false, testMeal.getIsVegetarian());
                assertEquals(false, testMeal.getIsGlutenFree());

                //set 2 ingredient
                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(9999,9999,9999,9999);
                testFood.initCategories(false,false,false,false,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);

                testFood = new Edible();
                testMealIngredient = new Ingredient();
                testFood.initNutrition(20,20,20,20);
                testFood.initCategories(false,false,false,true,true);
                testMealIngredient.setIngredient(testFood);
                testIngredients.add(testMealIngredient);
                //set 2 ingredient

                testIngredients.add(testMealIngredient);

                testMeal.setIngredients(testIngredients);

            }
            catch (Exception e)
            {
                fail("Should not throw exception");
            }
            // problem mark 1
//             testNutrition(testMeal,9999,9999,9999,9999);
            assertEquals(9999, testMeal.getCalories());
            assertEquals(9999, testMeal.getProtein());
            assertEquals(9999, testMeal.getCarbs());
            assertEquals(9999, testMeal.getFat());

//             testCategories(testMeal,false,false,false,true,true);
            assertEquals(false, testMeal.getIsAlcoholic());
            assertEquals(false, testMeal.getIsSpicy());
            assertEquals(false, testMeal.getIsVegan());
            assertEquals(true, testMeal.getIsVegetarian());
            assertEquals(true, testMeal.getIsGlutenFree());
        }


    }

    @Nested
    @DisplayName("Empty case")
    class Test_Empty {

        private Meal testMeal;
        // this is for Edible class for Ingredient class
        private Edible testFood;
        // this is for Edible class for Ingredient class
        private Ingredient testMealIngredient;
        private ArrayList<Ingredient> testIngredients;

        @BeforeEach
        void setup(){
            testMeal = new Meal();
            testFood = new Edible();
            testMealIngredient = new Ingredient();
            testIngredients = new ArrayList<Ingredient>();

        }

        @Test
        void test_setInstructions(){ // null string
            try {
                testMeal.setInstructions(null);
                fail("should not success set a null instruction string to the Meal");
            }
            catch (Exception e){
                assertTrue(e instanceof IOException);
                assertEquals("Invalid instructions",e.getMessage());
            }

            try {
                testMeal.setInstructions("");

            }
            catch (Exception e){
                fail("should success set a empty instruction string to the Meal");
            }
            assertEquals("",testMeal.getInstructions());

        }


        @Test
        void test_setIngredient()
        {
            try {
                testMeal.setIngredients(null);
                fail("should  not success set a null ingredients arraylist to Meal");
            }
            catch ( Exception e){
                assertTrue(e instanceof IOException);
                assertEquals("Invalid meal ingredients",e.getMessage());
            }

            try {
                testMeal.setIngredients(testIngredients);
            }
            catch ( Exception e){
                fail("should success set a empty ingredients arraylist to Meal");
            }
            // problem mark 2
            //if the input ingredients arraylist the size is 0 should let all the Nutrition and categories to default
            // but some of Categories is not set to default

//             testNutrition(testMeal,0,0,0,0);
            assertEquals(0, testMeal.getCalories());
            assertEquals(0, testMeal.getProtein());
            assertEquals(0, testMeal.getCarbs());
            assertEquals(0, testMeal.getFat());

//             testCategories(testMeal,false,false,false,false,false);
            assertEquals(false, testMeal.getIsAlcoholic());
            assertEquals(false, testMeal.getIsSpicy());
            assertEquals(false, testMeal.getIsVegan());
            assertEquals(false, testMeal.getIsVegetarian());
            assertEquals(false, testMeal.getIsGlutenFree());
        }


    }

    @Nested
    @DisplayName("Invalid case")
    class Test_Invalid {

        private Meal testMeal;
        // this is for Edible class for Ingredient class
        private Edible testFood;
        // this is for Edible class for Ingredient class
        private Ingredient testMealIngredient;
        private ArrayList<Ingredient> testIngredients;

        @BeforeEach
        void setup(){
            testMeal = new Meal();
            testFood = new Edible();
            testMealIngredient = new Ingredient();
            testIngredients = new ArrayList<Ingredient>();

        }



        @Test
        void test_setIngredient()
        {
            testIngredients.add(null);
            try{
                testMeal.setIngredients(testIngredients);
                fail("there can not have null obj in ingredients list");
            }
            catch ( Exception e){
                assertTrue(e instanceof IOException);
                assertEquals("Invalid meal ingredients",e.getMessage());
            }
        }


    }

}


