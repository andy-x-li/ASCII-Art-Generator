import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.Color

def color_to_ASCII(n : Int) : String = n match 
    // returns a ascii character depending on grayscale value
    case n if (n >= 0 && n < 20) => "M"
    case n if (n >= 20 && n < 42) => "#"
    case n if (n >= 42 && n < 64) => "$"
    case n if (n >= 64 && n < 86) => "&"
    case n if (n >= 86 && n < 108) => "*"
    case n if (n >= 108 && n < 138) => "x"
    case n if (n >= 138 && n < 170) => "+"
    case n if (n >= 170 && n < 202) => "="
    case n if (n >= 202 && n < 234) => "~"
    case _ => "-"

def photo_to_ASCII(img: BufferedImage) = {
  // dimensions of image
  val w = img.getWidth
  val h = img.getHeight

  // main art for loop 
  for (x <- 0 until h by 8)
    for (y <- 0 until w by 6)

      // gets image rgb, and finds the grayscale by taking the average
      var curr = new Color(img.getRGB(y, x))
      val red = curr.getRed()
      val green = curr.getGreen()
      val blue = curr.getBlue()
      val grayscale = (red + green + blue) / 3

      // calls function that gets the corresponding ascii character to the grayscale
      print(color_to_ASCII(grayscale))

    // next line of ascii art
    println()
}
  
@main def ASCIIArt(file1 : String) = {
  // read original image, and obtain width and height
  val photo = ImageIO.read(new File(file1))
  
  // calls function that draws ascii art 
  photo_to_ASCII(photo) 
}