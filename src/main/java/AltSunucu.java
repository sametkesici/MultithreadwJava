package main.java;
public class AltSunucu extends Thread {

    // 1-50 arası istek alır
    // 1-50 arası cevap verir

    AnaSunucu anasunucu;

    public AltSunucu(AnaSunucu anasunucu) {
        this.anasunucu = anasunucu;
    }
    private boolean running = true;
    public int AltKapasite=0;

    public void run(){

        while(running)
        {
            istekAl();
            cevapVer();
        }
    }
    public synchronized void istekAl()
    {

        int a = anasunucu.altSunucu�stek(anasunucu.AnaKapasite);

        anasunucu.AnaKapasite -= a;

        if(anasunucu.AnaKapasite <= 0 )
        {
            anasunucu.AnaKapasite = 0;
        }else if(anasunucu.AnaKapasite > 10000)
        {
            anasunucu.AnaKapasite = 10000;
        }


        if(AltKapasite + a >= 5000)
        {
            AltKapasite = 5000;
        }else
        {
            AltKapasite = AltKapasite + a;
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public synchronized void cevapVer()
    {
        int x = (int)(Math.random()*75);
        
        if(AltKapasite - x <= 0 )
        {
            AltKapasite = 0;
        }else
        {
            AltKapasite = AltKapasite - x;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void kapa()
    {
        running = false;
    }

}

   
