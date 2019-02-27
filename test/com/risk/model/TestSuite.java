package com.risk.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContinentReadTest.class,
        CountryEmptyValidation.class,
        ContinentReadTest.class,
        EmptyContinentValidation.class,
        MapRiskModelSetNeighbouringCountryTest.class,
        MoveValidationTest.class,
        MapUnlinkingValidation.class,
        NoOfArmyReinforcementTest.class,

})

/** Test suite */

public class TestSuite {
}
