package champollion;

import lombok.Getter;
import lombok.Setter;

public class Intervention {
    // Attributs

    @Getter // Ajoute les getters
    @Setter // Ajoute les setters
    private String date;

    @Getter // Ajoute les getters
    @Setter
    private int duree;

    @Getter // Ajoute les getters
    @Setter
    private boolean annulee;

    @Getter // Ajoute les getters
    @Setter
    private int h_deb;

    @Getter // Ajoute les getters
    @Setter
    private UE ue;

    public Intervention(String date, int duree, int h_deb, UE ue) {
        this.date = date;
        this.duree = duree;
        this.annulee = false;
        this.h_deb = h_deb;
        this.ue = ue;
    }

    public int getDuree(){
        return duree;
    }
}
