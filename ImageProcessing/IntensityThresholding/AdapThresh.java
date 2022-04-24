import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.Arrays.*;


/**
 * AdapThresh is an algorithm to apply adaptive Mean-C thresholding to an image.
 * 
 */

public class AdapThresh extends Thread {

  // the width and height of this image in pixels
  private int i_w, i_h;

  // pixel arrays for input and output images
  private int[] src_1d;
  private int[] dest_1d;
  private int mean;

  /**
   * Applies the adaptive thresholding operator to the specified image array
   * using the mean function to find the threshold value
   *
   * @param src    pixel array representing image to be thresholded
   * @param width  width of the image in pixels
   * @param height height of the image in pixels
   * @param size   the size of the neigbourhood used in finding the threshold
   * @param con    the constant value subtracted from the mean
   * @return a thresholded pixel array of the input image array
   */

  public int[] mean_thresh(int[] src, int width, int height, int size,
      int con) {

    i_w = width;
    i_h = height;
    src_1d = new int[i_w * i_h];
    dest_1d = new int[i_w * i_h];
    
    mean = 0;
    int count = 0;
    src_1d = src;
    int[][] tmp_2d = new int[i_w][i_h];

    // First convert input array from 1_d to 2_d for ease of processing
    for (int i = 0; i < i_w; i++) {
      for (int j = 0; j < i_h; j++) {
        tmp_2d[i][j] = src_1d[i + (j * i_w)] & 0x000000ff;
      }
    }

    // Now find the sum of values in the size X size neigbourhood
    for (int j = 0; j < i_h; j++) {
      for (int i = 0; i < i_w; i++) {
        mean = 0;
        count = 0;
        // Check the local neighbourhood
        for (int k = 0; k < size; k++) {
          for (int l = 0; l < size; l++) {
            try {
              mean = mean + tmp_2d[(i - ((int) (size / 2)) + k)][(j - ((int) (size / 2)) + l)];
              count++;
            }
            // If out of bounds then ignore pixel
            catch (ArrayIndexOutOfBoundsException e) {
            }
          }
        }
        // Find the mean value
        mean = (int) (mean / count) - con;

        // Threshold below the mean
        if (tmp_2d[i][j] > mean) {
          dest_1d[i + (j * i_w)] = 0xffffffff;
        } else {
          dest_1d[i + (j * i_w)] = 0xff000000;
        }
      }
    }
    return dest_1d;
  }

  public int getMean(){
    return mean;
  }

}