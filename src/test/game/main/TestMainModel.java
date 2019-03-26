package test.game.main;

import entity.Continent;
import entity.Country;
import entity.Player;
import risk.game.main.MainModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static junit.framework.TestCase.*;

/**
 * This test class verifies main model functionality
 *
 * @author iamdc003, jasmeet, shareenali
 * @version 0.2
 */
public class TestMainModel {
    private MainModel mainModel = new MainModel();

    @Before
    public void before() {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Continent> continents = new ArrayList<>();
        Continent continent = new Continent("Asia", 5);
        players.add(new Player("dhaval", 0));
        players.add(new Player("shareen", 1));

        this.mainModel.setPlayers(players);

        ArrayList<Country> countries = new ArrayList<>();
        Country country1 = new Country("India", "Asia", 1, 1);
        Country country2 = new Country("Russia", "Asia", 1, 1);
        Country country3 = new Country("China", "Asia", 1, 1);
        Country country4 = new Country("Pakistan", "Asia", 1, 1);
        Country country5 = new Country("Mongolia", "Asia", 1, 1);
        Country country6 = new Country("Bengal", "Asia", 1, 1);

        country1.addNeighbour(country2.getName());
        country2.addNeighbour(country1.getName());
        country3.addNeighbour(country4.getName());
        country4.addNeighbour(country3.getName());
        country4.addNeighbour(country5.getName());
        country5.addNeighbour(country4.getName());
        country6.addNeighbour(country5.getName());
        country5.addNeighbour(country6.getName());

        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
        countries.add(country4);
        countries.add(country5);
        countries.add(country6);

        continents.add(continent);

        this.mainModel.setMapContent(countries, continents);
    }

    /**
     * Test Case method for calculation of reinforcement armies
     */
    @Test
    public void reinforcementArmies() {
        int armiesToAssign, controlValue = 0;
        armiesToAssign = (int) (int) Math.round(Math.floor((float) 3 / 3) < 3
                ? 3 : Math.floor((float) 3 / 3));

        armiesToAssign += controlValue;
        this.mainModel.resetArmiesToAssign(this.mainModel.getPlayer("dhaval").getName());
        int armiesAvailableToAssign = this.mainModel.getArmiesAvailableToAssign();
        assertEquals(armiesAvailableToAssign, armiesToAssign);
    }

    /**
     * Test Case method for calculation of reinforcement armies after
     */
    @Test
    public void calculateReinforcementArmies() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 1);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 1);
        this.mainModel.getPlayer("shareen").setArmies("Bengal", 1);
        this.mainModel.getPlayer("dhaval").setArmies("India", 1);
        this.mainModel.getPlayer("dhaval").setArmies("China", 1);
        this.mainModel.getPlayer("dhaval").setArmies("Mongolia", 1);
        int updatedArmies = 4;
        this.mainModel.reinforcementPhase(this.mainModel.getPlayer("dhaval").getName(), this.mainModel.getCountries().get("India").getName(), 3);
        assertEquals(this.mainModel.getPlayer("dhaval").getArmiesInCountry("India"), updatedArmies);
    }

    /**
     * Test Case method to Check link between countries
     */
    @Test
    public void checkConnectionFort() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 1);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 1);
        this.mainModel.getPlayer("shareen").setArmies("Bengal", 1);
        this.mainModel.getPlayer("dhaval").setArmies("India", 1);
        this.mainModel.getPlayer("dhaval").setArmies("China", 1);
        this.mainModel.getPlayer("dhaval").setArmies("Mongolia", 1);
        boolean resultExcpected = true;
        boolean resultActual = this.mainModel.checkForLink(new ArrayList<>(), "India", "Russia");
        assertEquals(resultActual, resultExcpected);
    }

    /**
     * Test Case method to check the domination of country
     */
    @Test
    public void checkDomination() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 1);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 1);
        this.mainModel.getPlayer("shareen").setArmies("Bengal", 1);
        this.mainModel.getPlayer("dhaval").setArmies("India", 1);
        this.mainModel.getPlayer("dhaval").setArmies("China", 1);
        this.mainModel.getPlayer("dhaval").setArmies("Mongolia", 1);
        String dom = "50.0%";
        String[] result = this.mainModel.getDominationRow(this.mainModel.getPlayer("dhaval"));
        assertEquals(result[3], dom);
    }

    /**
     * Test Case method to check the assignment of country
     */
    @Test
    public void checkCountryAssignment() {
        this.mainModel.assignCountry();
        assertEquals(this.mainModel.getPlayer("dhaval").getCountries().size(), 3);
    }

    /**
     * Test Case method to check the assignment of armies
     */
    @Test
    public void checkAssignArmies() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 1);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 1);
        this.mainModel.getPlayer("shareen").setArmies("Bengal", 1);
        this.mainModel.getPlayer("dhaval").setArmies("India", 1);
        this.mainModel.getPlayer("dhaval").setArmies("China", 1);
        this.mainModel.getPlayer("dhaval").setArmies("Mongolia", 1);
        this.mainModel.assignArmies();
        int total = this.mainModel.getPlayer("dhaval").getArmiesInCountry("India") + this.mainModel.getPlayer("dhaval").getArmiesInCountry("China") + this.mainModel.getPlayer("dhaval").getArmiesInCountry("Mongolia");
        assertEquals(total, 43);
    }


    /**
     * Test case method to check the possibility of attack
     */
    @Test
    public void checkAttackPosibility() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 10);
        this.mainModel.getPlayer("dhaval").setArmies("India", 4);
        boolean result = this.mainModel.checkIfAttackFeasible(this.mainModel.getPlayer("shareen"), "Russia", "India");
        assertEquals(true, result);
    }

    /**
     * Test case method verify that there is no trace of a player who is out of the game after losing all the countries
     */
    @Test
    public void checkOnePlayerWinsAttackAndOtherWipedOff() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 10);
        this.mainModel.getPlayer("dhaval").setArmies("India", 4);
        this.mainModel.getPlayer("dhaval").setVictory(false);
        this.mainModel.updateEntitiesAfterAttack(this.mainModel.getPlayer("dhaval"), this.mainModel.getPlayer("shareen"), "India");
        ArrayList<String> playerNames = this.mainModel.getPlayerNames();
        boolean ifPresent = playerNames.contains(this.mainModel.getPlayer("dhaval").getName());
        assertEquals(true, !ifPresent);
    }

    /**
     * Test case method verify if all the countries in map are conquered by attacker, attacker is declared winner and game end
     */
    @Test
    public void checkAttackerConqueredCountries() {
        this.mainModel.getPlayer("dhaval").setArmies("India", 12);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 2);
        this.mainModel.getPlayer("shareen").setVictory(false);
        this.mainModel.updateEntitiesAfterAttack(this.mainModel.getPlayer("shareen"), this.mainModel.getPlayer("dhaval"), "Pakistan");
        int sizeOfConqueredCountries = this.mainModel.getPlayer("dhaval").getCountries().size();
        assertEquals(2, sizeOfConqueredCountries);
    }

    /**
     * Test case method to verify all out mode
     */
    @Test
    public void checkAllOutMode() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 12);
        this.mainModel.getPlayer("dhaval").setArmies("India", 2);
        boolean defendantVictory = this.mainModel.attackPhase(this.mainModel.getPlayer("shareen").getName(), this.mainModel.getPlayer("dhaval").getName(), "Russia", "India", true);
        assertEquals(true, !defendantVictory);
    }


    /**
     * Test case method to verify armies of attacker after the attack
     */
    @Test
    public void verifyAttackerArmiesAfterAttack() {
        this.mainModel.getPlayer("dhaval").setArmies("India", 4);
        this.mainModel.getPlayer("dhaval").setArmies("Pakistan", 3);
        this.mainModel.processPostAttackPhase(this.mainModel.getPlayer("dhaval"), "India", "Pakistan", 2);
        int srcCountryArmy = this.mainModel.getPlayer("dhaval").getArmiesInCountry("India");
        assertEquals(2, srcCountryArmy);
    }

    /**
     * Test case method verify that the defender doesn’t have the country if he loses an incoming attack
     */
    @Test
    public void checkDefendantCountriesAfterAttack() {
        this.mainModel.getPlayer("dhaval").setArmies("India", 7);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 2);
        this.mainModel.getPlayer("shareen").setVictory(false);
        this.mainModel.updateEntitiesAfterAttack(this.mainModel.getPlayer("shareen"), this.mainModel.getPlayer("dhaval"), "Pakistan");
        boolean defendantCountryflag = this.mainModel.getPlayer("shareen").getCountries().containsKey("Pakistan");
        assertEquals(false, defendantCountryflag);
    }


    /**
     * Test case method  to compute dice rolls
     */
    @Test
    public void checkNoOfDiceRolls() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 4);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 1);
        this.mainModel.getPlayer("shareen").setArmies("Bengal", 1);
        this.mainModel.getPlayer("dhaval").setArmies("India", 2);
        this.mainModel.getPlayer("dhaval").setArmies("China", 3);
        this.mainModel.getPlayer("dhaval").setArmies("Mongolia", 1);
        this.mainModel.determineNoOfDiceRolls("Russia", this.mainModel.getPlayer("shareen"), true);
        int diceRolls = this.mainModel.getPlayer("shareen").getNoOfDiceRolls();
        int expectedDiceRolls = 3;
        assertEquals(diceRolls, expectedDiceRolls);

    }

    /**
     * Test case method  to check minimum armies for attack
     */
    @Test
    public void checkMinArmiesForAttackPhase() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 4);
        this.mainModel.getPlayer("dhaval").setArmies("Pakistan", 1);
        boolean checkArmiesForAttack = this.mainModel.checkMinArmiesForAttack(this.mainModel.getPlayer("shareen"), "Russia");
        assertEquals(true, checkArmiesForAttack);

    }


    /**
     * Test case method  to Check the number of cards assigned after start up phase should be 1
     */
    @Test
    public void checkCardAssignmentAfterStartUp() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 4);
        HashMap<String, Country> countries = this.mainModel.getCountries();
        this.mainModel.assignInitialCards();
        Country country = countries.get("Russia");
        boolean cardTypeFlag = country.getCardType().length() == 0;
        assertEquals(false, cardTypeFlag);
    }


    /**
     * Test case method  to  player will initially get 5 armies
     */
    @Test
    public void checkInitialCardExchange() {
        this.mainModel.getPlayer("shareen").setArmies("Russia", 4);
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            al.add("Cavalry");
        }
        this.mainModel.resetArmiesToAssign("shareen");
        this.mainModel.getPlayer("shareen").setCards(al);
        this.mainModel.addArmiesOnCardsAvail(this.mainModel.getPlayer("shareen"));
        int armyCount = this.mainModel.getArmiesAvailableToAssign();
        assertEquals(8, armyCount);

    }

    /**
     * Test case method  to Check the player will get 5+5 armies once card is availed
     */
    @Test
    public void checkArmyAssignmentWhenCardIsAvailedMoreThanOnce() {
        this.mainModel.getPlayer("dhaval").setArmies("Russia", 4);
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            al.add("Infantry");
        }
        this.mainModel.resetArmiesToAssign("dhaval");
        this.mainModel.getPlayer("dhaval").setCards(al);
        this.mainModel.addArmiesOnCardsAvail(this.mainModel.getPlayer("dhaval"));
        this.mainModel.addArmiesOnCardsAvail(this.mainModel.getPlayer("dhaval"));
        int armyCount = this.mainModel.getArmiesAvailableToAssign();
        assertEquals(18, armyCount);
    }

    /**
     * Test case method  to verify cards can be exchanged for armies if player has three cards of same sort or different sorts
     */
    @Test
    public void checkCardAssignedProperly() {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            al.add("Cavalry");
        }
        this.mainModel.getPlayer("dhaval").setCards(al);
        this.mainModel.addArmiesOnCardsAvail(this.mainModel.getPlayer("dhaval"));
        int cardCount = this.mainModel.getPlayer("dhaval").getCards().size();
        assertEquals(3, cardCount);
    }

    /**
     * Verify card is updated when Attacker wins
     */
    @Test
    public void checkCardUpdate() {
        this.mainModel.getPlayer("dhaval").setArmies("India", 12);
        this.mainModel.getPlayer("shareen").setArmies("Pakistan", 2);
        int cardBeforeAttack = this.mainModel.getPlayer("dhaval").getCards().size();
        this.mainModel.getPlayer("shareen").setVictory(false);
        this.mainModel.updateEntitiesAfterAttack(this.mainModel.getPlayer("shareen"), this.mainModel.getPlayer("dhaval"), "Pakistan");
        int cardsAfterAttack = this.mainModel.getPlayer("dhaval").getCards().size();
        boolean result = (cardBeforeAttack == cardsAfterAttack);
        assertEquals(false, result);
    }

}
