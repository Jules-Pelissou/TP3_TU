package champollion;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	@Test
	public void testReturnEDT(){
		untel.ajouteEnseignement(uml, 0, 10, 0);
		//assertEquals(, untel.getEDT(), "Cela doit renvoyer un dictionnaire d'enseignements");
	}

	@Test
	public void testAjouteIntervention() {
		untel.ajouteEnseignement(uml, 0, 10, 0);
		untel.ajouteIntervention("13-12-21", 5, 17,  uml);

		try {
			untel.ajouteIntervention("18-04-22", 10, 10, uml);
			fail("On ne doit pas pouvoir ajouter d'autres interventions");
		}catch(IllegalArgumentException e) {

		}

	}

	@Test
	public void testresteAPlanifier() {
		untel.ajouteEnseignement(uml, 0, 20, 0);
		untel.ajouteIntervention("13-12-21", 10, 17,  uml);

		assertEquals(10, untel.resteAPlanifier(uml), "Le reste a planifier n'est pas le même");

	}

	@Test
	public void testEnSousServiceFalse() {
		untel.ajouteEnseignement(uml, 0, 20, 0);

		assertFalse(untel.enSousService());
	}

	@Test
	public void testEnSousServiceTrue(){
		untel.ajouteEnseignement(uml, 0, 192, 0);
		assertTrue(untel.enSousService());
	}

	@Test
	public void testAjouteInterventionListeVide() {

		untel.ajouteEnseignement(uml, 0, 20, 0);

		// Vérifiez que la liste des interventions pour cette UE est vide avant
		assertTrue(untel.getInterventions().isEmpty(), "La liste des interventions doit être vide avant l'ajout");

		// Appelez la méthode
		untel.ajouteIntervention("13-12-21", 10, 17, uml);

		// Vérifiez que l'intervention a bien été ajoutée
		assertFalse(untel.getInterventions().isEmpty(), "L'intervention aurait dû être ajoutée");
		assertEquals(1, untel.getInterventions().size());
	}
}
