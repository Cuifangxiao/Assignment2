package fisherman;

// I copied this class from the CSDN website and made some changes myself

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread {
    private final String fileName;
    public  int count;

    public Music(String wavFile,boolean isLoop) {
        this.fileName = wavFile;
        if(isLoop) count = 99999;
        else count = 1;
    }

    public void run() {
        //Name of the play music
        File soundFile = new File(fileName);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found:" + fileName);
            return;
        }
        int i = 0;
        //Whether to loop
        while (i < count) {
            AudioInputStream audioInputStream;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException | IOException e1) {
                e1.printStackTrace();
                return;
            }
            AudioFormat format = audioInputStream.getFormat(); // 音频格式
            SourceDataLine Pauline; // 源数据线
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try {
                Pauline = (SourceDataLine) AudioSystem.getLine(info);
                Pauline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (Pauline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) Pauline.getControl(FloatControl.Type.PAN);
            }
            Pauline.start();
            int nBytesRead = 0;
            int EXTERNAL_BUFFER_SIZE = 524288;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        Pauline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                Pauline.drain();
            }
            i++;
        }
    }
}