package com.example.chennai360.Data;

import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;

import com.example.chennai360.Constants.MyConstants;
import com.example.chennai360.Database.RoomDB;
import com.example.chennai360.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AppData extends Application {
    RoomDB database;
    Context context;
    String category;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData() {
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<>();
        basicItem.clear();

        basicItem.add(new Items("Visa", category, false));
        basicItem.add(new Items("Password", category, false));
        basicItem.add(new Items("Tickets", category, false));
        basicItem.add(new Items("Wallet", category, false));
        basicItem.add(new Items("Driving License", category, false));
        basicItem.add(new Items("Currency", category, false));
        basicItem.add(new Items("House key", category, false));
        basicItem.add(new Items("Book", category, false));
        basicItem.add(new Items("Travel Pillow", category, false));
        basicItem.add(new Items("Eye Patch", category, false));
        basicItem.add(new Items("Umbrella", category, false));
        basicItem.add(new Items("Note Book", category, false));
        return basicItem;
    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Tooth-brush", "Tooth-pasts", "Floss", "Mouthwash", "Shaving Cream", "Razor Blade",
        "Soap", "Fiber", "Shampoo", "Hair Conditioner", "Brush", "Comb", "hair Dryer", "Curling Iron",
        "Hair Moulder", "Hair Clip","Moisturizer", "Lip Cream", "Contact lens", "Perfume", "Deodorant",
        "Mail Polish Remover", "Tweezers", "Nail Scissors", "Nail Files", "Suntan Cream"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"Shirts", "Casual Dress", "Evening Dress", "Cardigan", "Rain Coat", "Glove"};
        return  prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Snapsuit", "Outfit", "Jumpsuit", "baby Socks"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getHealthData() {
        String[] data = {"Aspirin", "Drugs Used", "Vitamins Used", "Lens Solution", "Hot Water Bag",
        "Replacement Lens", "Fever Reducer", "Pain Relieve Spray"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE, data);
    }

    public List<Items> getFoodData() {
        String[] data = {"Snack", "Sandwich", "juice", "Tea Bags", "Coffee", "Water", "Baby Food"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getBeachSuppliesData() {
        String[] data = {"Sea Glasses", "Sea Bed", "Suntan Cream", "Beach Umbrella",
        "Swim Fins", "Sunbed"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getTechnologyData() {
        String[] data = {"Mobile", "laptop", "Charger", "Headphones",
                "USB", "Watch"};
        return  prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

    public List<Items> getCarSuppliesData() {
        String[] data = {"Pump", "Car Jack", "Spare Car Key", "Accident Record Set", "Car Cover"};
        return  prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getNeedsData() {
        String[] data = {"Backpack", "Daily Bags", "Laundry Bag", "Travel Lock",
        "Sport Equipment", "Important Numbers"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE, data);
    }

    public List<Items> prepareItemsList(String category, String[] data) {
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();

        for(int i=0 ;i <list.size(); i++) {
            dataList.add(new Items(list.get(i),category, false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData() {
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getPersonalCareData());
        listOfAllItems.add(getBabyNeedsData());
        listOfAllItems.add(getHealthData());
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSuppliesData());
        listOfAllItems.add(getNeedsData());
        return listOfAllItems;
    }

    public void persistAllData(){
        List<List<Items>> listOfALlItems = getAllData();
        for(List<Items> list: listOfALlItems) {
            for (Items items : list) {
                database.mainDao().saveItem(items);
            }
        }
        System.out.println("Data Added");
    }
    
    public void persistDataByCategory(String category, Boolean onlyDelete) {
        try {
            List<Items> list = deleteAndGetListByCategory(category,onlyDelete);
            if(!onlyDelete) {
                for(Items items : list) {
                    database.mainDao().saveItem(items);
                    Toast.makeText(context, category+" Reset Successfully", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, category+" Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Items> deleteAndGetListByCategory(String category, Boolean onlyDelete) {
        if(onlyDelete) {
            database.mainDao().deleteALlByCategoryAndAddedBy(category, MyConstants.SYSTEM_SMALL);
        } else {
            database.mainDao().deleteAllByCategory(category);
        }

        switch (category) {
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();

            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();

            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();

            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();

            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();

            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();

            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();

            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();

            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSuppliesData();

            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();

        }
    }

}
