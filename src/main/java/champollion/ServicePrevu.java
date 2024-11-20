package champollion;

import lombok.Getter;

public class ServicePrevu {
	// TODO : implémenter cette classe
    // Attributs
    protected int volumeCM;
    protected int volumeTD;
    protected int volumeTP;
    protected UE ue;

    public ServicePrevu(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        this.ue = ue;
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
    }

    public void setVolumeCM(int volumeCM){
        this.volumeCM = volumeCM;
    }
    public void setVolumeTD(int volumeTD){
        this.volumeTD = volumeTD;
    }
    public void setVolumeTP(int volumeTP){
        this.volumeTP = volumeTP;
    }
    public void setUE(UE ue){
        this.ue = ue;
    }
}
