package com.risk.controller.map.createcountry;

import com.risk.controller.map.countryconnect.MapCountryConnectController;
import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCreateCountryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Map country Controller
 * @author Namita Faujdar 
 */

public class MapCreateCountryController implements ActionListener {

    private MapRiskModel d_mapRiskModel;
    private MapCreateCountryView d_mapCreateCountryView;
    private ContinentModel d_newContinentModel;

    private HashMap<String, ArrayList<Point>> d_mapPointList;
    private HashMap<String, Color> d_colorMapList;
    private  HashMap<String, Integer> d_indexMap;

    /**
     * Constructor initializes values and sets the screen to visible
     * @param new_mapRiskModel
     * @param new_mapPointList
     * @param new_colorMapList
     * @param new_indexMap
     */

    public MapCreateCountryController(MapRiskModel new_mapRiskModel, HashMap<String, ArrayList<Point>> new_mapPointList,
                                   HashMap<String, Color> new_colorMapList, HashMap<String, Integer> new_indexMap) {
        d_mapRiskModel = new_mapRiskModel;
        d_mapPointList = new_mapPointList;
        d_colorMapList = new_colorMapList;
        d_indexMap = new_indexMap;
        d_mapCreateCountryView = new MapCreateCountryView(d_mapRiskModel.getContinentModelList());
        d_mapCreateCountryView.setVisible(true);
        d_mapRiskModel.addObserver(d_mapCreateCountryView);
        d_mapCreateCountryView.setActionListener(this);
    }
    /**
     * @param e Performs action whenever there is a change in MapCreateCountryView class
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        CountryModel countryModel = new CountryModel();

        if (e.getSource().equals(d_mapCreateCountryView.addButton))
        {
            if (d_mapCreateCountryView.countryValue.getText() != null
                    && !d_mapCreateCountryView.countryValue.getText().equals(""))
            {

                if (sameCountryNameValidation()) {
                    JOptionPane.showOptionDialog(null, "Please enter a different country", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                    return;
                } else {
                    d_newContinentModel = (ContinentModel) d_mapCreateCountryView.continentListCombobox.getSelectedItem();
                    countryModel.setContinentName(d_newContinentModel.getContinentName());
                    countryModel.setCountryName(d_mapCreateCountryView.countryValue.getText());

                    countryModel.setBackground(Color.WHITE);
                    countryModel.setBorderColor(Color.BLACK);
                    d_mapRiskModel.getCountryModelList().add(countryModel);
                    d_mapRiskModel.setCountryModelList(d_mapRiskModel.getCountryModelList());
                }

            }
            else
            {
                JOptionPane.showOptionDialog(null, "Please enter a valid input", "Invalid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                return;
            }
        } else if (e.getSource().equals(d_mapCreateCountryView.nextButton)) {
            if (emptyContinentNameValidation()) {
                JOptionPane.showOptionDialog(null, "Please enter at least one country for each continent", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                return;
            } else {


                for (int i = 0; i < d_mapRiskModel.getCountryModelList().size(); i++) {

                    ArrayList<Point> pointList = d_mapPointList.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName());

                    int index = d_indexMap.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName());

                    System.out.println("==>" + d_mapPointList.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).x);
                    d_mapRiskModel.getCountryModelList().get(i).setXPosition(d_mapPointList.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).x);
                    d_mapRiskModel.getCountryModelList().get(i).setYPosition(d_mapPointList.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).y);
                    d_mapRiskModel.getCountryModelList().get(i).setBackgroundColor(d_colorMapList.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName()));

                    d_indexMap.put(d_mapRiskModel.getCountryModelList().get(i).getContinentName(),d_indexMap.get(d_mapRiskModel.getCountryModelList().get(i).getContinentName())+1);

                    //	}

                }

                new MapCountryConnectController(d_mapRiskModel);
                d_mapCreateCountryView.dispose();
            }
        }
    }

    /**
     * Check for same country validation
     * @return boolean
     */
    private boolean sameCountryNameValidation() {

        for (CountryModel countryModel : d_mapRiskModel.getCountryModelList()
        ) {
            if (countryModel.getCountryName().equals(d_mapCreateCountryView.countryValue.getText()))
                return true;
            }

        return false;
    }

    /**
     * Check for empty continent Value
     * @return boolean
     */
    public boolean emptyContinentNameValidation() {
        java.util.List<ContinentModel> listOfContinents = d_mapRiskModel.getContinentModelList();
        List<CountryModel> listOfCountries = d_mapRiskModel.getCountryModelList();
        String continentName = " ";
        for (int i = 0; i < listOfContinents.size(); i++) {
            continentName = listOfContinents.get(i).getContinentName();
            int count = 0;
            for (int j = 0; j < listOfCountries.size(); j++) {
                count++;
                if (continentName.equals(listOfCountries.get(j).getContinentName())) {
                    count = 0;
                    break;
                }
            }
            if (count == listOfCountries.size()) {
                return true;
            }
        }
        return false;
    }
}
