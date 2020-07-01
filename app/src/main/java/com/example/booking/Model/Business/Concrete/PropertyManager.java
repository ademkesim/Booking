package com.example.booking.Model.Business.Concrete;

import com.example.booking.Model.Business.Abstract.IPropertyService;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyManager implements IPropertyService {
    private AppDatabase _appDatabase;
    public PropertyManager(AppDatabase appDatabase) {
        _appDatabase = appDatabase;
    }

    @Override
    public List<Property> GetList(int userId) {
        return  _appDatabase.propertyService().GetList(userId);
    }

    @Override
    public Property GetById(int propertyId) {
        return _appDatabase.propertyService().GetById(propertyId);
    }

    public ArrayList<Property> ArrayGetList(int userId) {
        List<Property> listProperty = GetList(userId);
        ArrayList<Property> listOfProperty = new ArrayList<>(listProperty.size());
        for (Object object : listProperty) {
            listOfProperty.add((Property) object);
        }
        return listOfProperty;
    }

    @Override
    public void Add(Property property) { _appDatabase.propertyService().Add(property); }

    @Override
    public void Update(Property property) { _appDatabase.propertyService().Update(property); }

    @Override
    public void Delete(Property property) { _appDatabase.propertyService().Delete(property);}
}
