package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.MapRiskModel;

import java.util.*;

/**
 * This class is to check the validation of the map
 *
 * @author gursimransingh
 */
public class MapValidation {

    /**
     * Check of validation of continents
     *
     * @param mapModel
     *
     */
    public boolean unlinkedContinentValidation(MapRiskModel mapModel) {
        boolean flag = true;
        List<CountryModel> listOfCountrys = mapModel.getCountryModelList();
        List<CountryModel> listOfLinkedCountries;

        HashMap<CountryModel, CountryModel> listOfCountriesInContinent = new HashMap<CountryModel, CountryModel>();

        for (int i = 0; i < mapModel.getContinentModelList().size(); i++) {
            for (int j = 0; j < listOfCountrys.size(); j++) {
                if (mapModel.getContinentModelList().get(i).getContinentName()
                        .equals(listOfCountrys.get(j).getContinentName())) {
                    listOfCountriesInContinent.put(listOfCountrys.get(j), listOfCountrys.get(j));
                }

            }
            Iterator it = listOfCountriesInContinent.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<CountryModel, CountryModel> pair = (Map.Entry<CountryModel, CountryModel>) it.next();
                listOfLinkedCountries = pair.getValue().getConnectedCountryList();
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

    /**
     * check for link is empty for continents
     *
     * @param mapModel
     * @return boolean
     */
    public boolean emptyLinkCountryValidation(MapRiskModel mapModel) {
        List<CountryModel> listOfCountrys = mapModel.getCountryModelList();
        List<CountryModel> linkedCountry;
        for (int j = 0; j < listOfCountrys.size(); j++) {
            linkedCountry = listOfCountrys.get(j).getConnectedCountryList();
            if (linkedCountry == null || linkedCountry.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for Inter Linked Continent
     *
     * @param mapModel
     * @return boolean
     */
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
                        for (int a = 0; a < listOfCountrys.get(k).getConnectedCountryList().size(); a++) {
                            if (Countryname.get(i)
                                    .equals(listOfCountrys.get(k).getConnectedCountryList().get(a).getCountryName())) {
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
     * End of game.
     *
     * @param gamePlayModel the game play model
     * @return true, if successful
     */
    public boolean endOfGame(GamePlayModel gamePlayModel) {
        for (int i = 0; i < gamePlayModel.getPlayers().size(); i++) {
            if (gamePlayModel.getPlayers().get(i).getPlayerCountries().size() == gamePlayModel.getGameMap()
                    .getCountryModelList().size()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check on empty Continent Validation
     *
     * @param mapModel
     * @return boolean
     */
    public boolean emptyContinentValidation(MapRiskModel mapModel) {
        List<ContinentModel> listOfContinents = mapModel.getContinentModelList();
        List<CountryModel> listOfCountrys = mapModel.getCountryModelList();
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
     * Check if valid move for fortification
     *
     * @param gameMapModel
     * @param fromCountryModel
     * @param toCountryModel
     * @return boolean
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
    Boolean isReachable(int s, int d, MapRiskModel gameMapModel, HashMap<CountryModel, Integer> mapOfCountries) {
        LinkedList<Integer> temp;

        boolean visited[] = new boolean[gameMapModel.getCountryModelList().size()];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        Iterator<Integer> i;
        while (queue.size() != 0) {
            s = queue.poll();

            int n;

            List<Integer> m = new ArrayList<Integer>();
            for (int l = 0; l < gameMapModel.getCountryModelList().get(s).getConnectedCountryList().size(); l++) {
                m.add(mapOfCountries.get(gameMapModel.getCountryModelList().get(s).getConnectedCountryList().get(l)));
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
