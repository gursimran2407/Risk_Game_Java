package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import java.util.*;

/**
 * @author gursimransingh
 */
public class MapValidation {
    public boolean continentLinkValidation(MapRiskModel mapRiskModel) {
        boolean flag = true;
        List<CountryModel> listOfCountrys = mapRiskModel.getCountryModelList();
        List<CountryModel> listOfLinkedCountries;

        HashMap<CountryModel, CountryModel> listOfCountriesInContinent = new HashMap<CountryModel, CountryModel>();

        for (int i = 0; i < mapRiskModel.getContinentModelList().size(); i++) {
            for (int j = 0; j < listOfCountrys.size(); j++) {
                if (mapRiskModel.getContinentModelList().get(i).getContinentName()
                        .equals(listOfCountrys.get(j).getContinentName())) {
                    listOfCountriesInContinent.put(listOfCountrys.get(j), listOfCountrys.get(j));
                }

            }
            Iterator it = listOfCountriesInContinent.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<CountryModel, CountryModel> pair = (Map.Entry<CountryModel, CountryModel>) it.next();
                listOfLinkedCountries = pair.getValue().getLinkCountryModel();
                int counter = 0;
                for (int k = 0; k < listOfLinkedCountries.size(); k++) {
                    if (listOfCountriesInContinent.containsValue((listOfLinkedCountries.get(k)))) {
                        counter++;
                    }
                }

                if (counter == listOfLinkedCountries.size() - 1) {
                    return false;
                }
            }

        }
        return flag;
    }


    public boolean emptyLinkCountryValidation(MapRiskModel mapRiskModel) {
        List<CountryModel> listOfCountrys = mapRiskModel.getCountryModelList();
        List<CountryModel> linkedCountry;
        for (int j = 0; j < listOfCountrys.size(); j++) {
            linkedCountry = listOfCountrys.get(j).getLinkCountryModel();
            if (linkedCountry == null || linkedCountry.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public boolean checkInterlinkedContinent(MapRiskModel mapModel) {

        String continent = "";
        List<ContinentModel> listOfContinents = mapModel.getContinentModelList();
        List<CountryModel> listOfCountrys = mapModel.getCountryModelList();
        List<String> Countryname = new ArrayList<String>();
        ;
        int numb;
        boolean emptyLinkContinent = false;
        for (int j = 0; j < listOfContinents.size(); j++) {
            continent = listOfContinents.get(j).getContinentName();
            numb = 0;
            for (int i = 0; i < listOfCountrys.size(); i++) {
                if (continent.equals(listOfCountrys.get(i).getContinentName())) {
                    Countryname.add(listOfCountrys.get(i).getCountryName());
                }
            }
            for (int i = 0; i < Countryname.size(); i++) {
                for (int k = 0; k < listOfCountrys.size(); k++) {
                    if (!continent.equals(listOfCountrys.get(k).getContinentName())) {
                        for (int a = 0; a < listOfCountrys.get(k).getLinkCountryModel().size(); a++) {
                            if (Countryname.get(i)
                                    .equals(listOfCountrys.get(k).getLinkCountryModel().get(a).getCountryName())) {
                                numb++;
                            }
                        }
                    }
                }
            }
            if (numb == 0) {
                emptyLinkContinent = true;
                return emptyLinkContinent;
            }
        }
        return emptyLinkContinent;

    }

    /**
     * Check on empty Continent Validation
     *
     * @param mapRiskModel
     * @return boolean
     */
    public boolean emptyContinentValidation(MapRiskModel mapRiskModel) {
        List<ContinentModel> listOfContinents = mapRiskModel.getContinentModelList();
        List<CountryModel> listOfCountrys = mapRiskModel.getCountryModelList();
        String continentName = " ";
        int numb;

        for (int i = 0; i < listOfContinents.size(); i++) {
            continentName = listOfContinents.get(i).getContinentName();
            numb = 0;
            for (int j = 0; j < listOfCountrys.size(); j++) {
                if (continentName.equals(listOfCountrys.get(j).getContinentName())) {
                    numb++;
                }
            }
            if (numb == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check on valid move for fortification
     *
     * @param gameMapModel
     * @param fromCountryModel
     * @param toCountryModel
     * @return
     */
    public boolean checkIfValidMove(MapRiskModel gameMapModel, CountryModel fromCountryModel,
                                    CountryModel toCountryModel) {

        LinkedList<CountryModel> temp;

        HashMap<CountryModel, Integer> mapOfCountries = new HashMap<CountryModel, Integer>();

        for (int i = 0; i < gameMapModel.getCountryModelList().size(); i++) {
            mapOfCountries.put(gameMapModel.getCountryModelList().get(i), i);
        }

        if (isReachable(mapOfCountries.get(fromCountryModel), mapOfCountries.get(toCountryModel), gameMapModel,
                mapOfCountries)) {
            return true;
        }
        return false;
    }

    // prints BFS traversal from a given source s
    Boolean isReachable(int s, int d, MapRiskModel mapRiskModel, HashMap<CountryModel, Integer> mapOfCountries) {
        LinkedList<Integer> temp;

        boolean visited[] = new boolean[mapRiskModel.getCountryModelList().size()];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        Iterator<Integer> i;
        while (queue.size() != 0) {
            s = queue.poll();

            int n;

            List<Integer> m = new ArrayList<Integer>();
            for (int l = 0; l < mapRiskModel.getCountryModelList().get(s).getLinkCountryModel().size(); l++) {
                m.add(mapOfCountries.get(mapRiskModel.getCountryModelList().get(s).getLinkCountryModel().get(l)));
            }

            i = m.listIterator();


            while (i.hasNext()) {
                n = i.next();

                // If this adjacent node is the destination node,
                // then return true
                if (n == d)
                    return true;

                // Else, continue to do BFS
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        // If BFS is complete without visited d
        return false;
    }
}
