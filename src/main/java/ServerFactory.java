package main.java;

import java.awt.Color;

import javax.swing.JProgressBar;

public class ServerFactory extends Thread {
    private AnaSunucu ana;
    int grid = 300;
    int grid2 = 47;
    public ServerFactory(AnaSunucu mainServer) {
        ana = mainServer;
    }
    public void run() {
        while (true) {
            for (int i = 0; i < ana.listeAd.size(); i++) {
                AltSunucu suan = ana.listeAd.get(i);
                if (suan.AltKapasite > 3500) {
                    AltSunucu yeni = new AltSunucu(ana);
                    yeni.AltKapasite = suan.AltKapasite * 50 / 100;
                    suan.AltKapasite = suan.AltKapasite - yeni.AltKapasite;
                    ana.listeAd.add(yeni);
                	JProgressBar progressBar_1 = new JProgressBar();
                	progressBar_1.setBackground(Color.white);
                	progressBar_1.setForeground(Color.decode("#F2545B"));
					progressBar_1.setMaximum(5000);
					progressBar_1.setStringPainted(true);
					progressBar_1.setBounds(grid2, grid, 300, 30);
					yazlab.frame.getContentPane().add(progressBar_1);
					ana.barList.add(progressBar_1);
					
					if(grid >= 700) {
						grid = 250;
						grid2 += 330;
						grid += 50;
					}else {
						grid += 50;
					}
					

                }
 
                    System.out.println((i+1)+".alt sunucu kapasitesi = " + ana.listeAd.get(i).AltKapasite );
                    System.out.println("main kapasite " + ana.AnaKapasite );

                    if (suan.AltKapasite == 0 && ana.listeAd.size() > 2) {
                        ana.listeAd.get(i).kapa();
                        ana.listeAd.remove(i);
                        
                        
                        yazlab.frame.getContentPane().remove(ana.barList.get(i));
                        ana.barList.remove(i);
                    }

                    for (int j = 0; j < ana.listeAd.size(); j++) {

                        if (ana.listeAd.get(j).isAlive() == false) {
                            ana.listeAd.get(j).start();
                        }
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
    }


