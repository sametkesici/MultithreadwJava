package main.java;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JProgressBar;

public class AnaSunucu extends Thread{

    // 1-100 arasÄ± istek alÄ±r
    // 1-50 arasÄ± cevap verir.
    public ArrayList<AltSunucu> listeAd = new ArrayList<AltSunucu>();
    public ArrayList<JProgressBar> barList = new ArrayList<JProgressBar>();
    public int AnaKapasite = 0 ;

    ServerFactory serverFactory = new ServerFactory(this);
    SunucuTakip sunucuTakip = new SunucuTakip(this);
    

    public AnaSunucu() {
        AltSunucu subserver = new AltSunucu(this);
        subserver.start();
        AltSunucu subserver2 = new AltSunucu(this);
        subserver2.start();
        listeAd.add(subserver);
    	JProgressBar progressBar_1 = new JProgressBar();
    	progressBar_1.setBackground(Color.white);
    	progressBar_1.setForeground(Color.decode("#F2545B"));
		progressBar_1.setMaximum(5000);
		progressBar_1.setStringPainted(true);
		progressBar_1.setBounds(47, 200, 300, 30);
		yazlab.frame.getContentPane().add(progressBar_1);
		barList.add(progressBar_1);
        listeAd.add(subserver2);
        JProgressBar progressBar_2 = new JProgressBar();
        progressBar_2.setBackground(Color.white);
        progressBar_2.setForeground(Color.decode("#F2545B"));
		progressBar_2.setMaximum(5000);
		progressBar_2.setStringPainted(true);
		progressBar_2.setBounds(47, 250, 300, 30);
		yazlab.frame.getContentPane().add(progressBar_2);
		barList.add(progressBar_2);
        serverFactory.start();
        sunucuTakip.start();
        
    }
    public void run()
    {

        while(true)
        {

            gelenÝstek();
            cevapSayisi();

        }
    }
    public synchronized void gelenÝstek()
    {
        int a = (int)(Math.random()*500);

        AnaKapasite = AnaKapasite + a;
        if(AnaKapasite > 10000)
        {
        	AnaKapasite = 10000;
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public synchronized void cevapSayisi()
    {
        int b = (int)(Math.random()*50);


        if (AnaKapasite - b <= 0) {
            AnaKapasite = 0;

        } else if (AnaKapasite - b > 0) {
            AnaKapasite = AnaKapasite - b;
        }

        if(AnaKapasite - altSunucuÝstek(AnaKapasite) <= 0)
        {
            AnaKapasite = 0 ;
        }else
        {
            AnaKapasite = AnaKapasite - altSunucuÝstek(AnaKapasite);
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public int altSunucuÝstek(int kontrol)
    {

    	int z = (int)(Math.random()*100);

    	if(z > kontrol)
        {
            return kontrol;
        }else
        {

            return z;
        }




    }
}
