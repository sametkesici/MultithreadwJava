package main.java;
public class SunucuTakip extends Thread {

    private AnaSunucu ana;

    public  SunucuTakip(AnaSunucu mainServer) {
        ana = mainServer;
    }
        public synchronized void  run() {
            while(true)
            {
                progressPercentage(ana.AnaKapasite, 10000);
                try {
                    SunucuTakip.sleep(500);
                } catch (Exception e) {
                }

                				

             }

            }


    public synchronized static void progressPercentage(int done, int total) {
            int size = 100;
            String iconLeftBoundary = "[";
            String iconDone = "=";
            String iconRemain = ".";
            String iconRightBoundary = "]";

            if (done > total) {
                throw new IllegalArgumentException();
            }
            int donePercents = (100 * done) / total;
            int doneLength = size * donePercents / 100;

            StringBuilder bar = new StringBuilder(iconLeftBoundary);
            for (int i = 0; i < size; i++) {
                if (i < doneLength) {
                    bar.append(iconDone);
                } else {
                    bar.append(iconRemain);
                }
            }
            bar.append(iconRightBoundary);
          //  System.out.print("\r" + bar + " " + donePercents + "% Main Thread" );

            if (done == total) {
                System.out.print("\n");
            }
    }



        }






