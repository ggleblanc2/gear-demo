# Gear Demonstration

## Introduction

Recently on Stack Overflow, a stgudent posted abouit his project to create a [gear simulator](https://stackoverflow.com/questions/75462877/how-to-rotate-images-every-tick#comment133147815_75462877).  It sounded like a good idea for a project, so I decided to give it a try.

Here's the GUI I came up with.  The gear demonstration starts with one gear in the center of the drawing panel.

![Initial Gear](readme-resources/geardemo1.png)

The spot on the gear is there to help you see the gear when it's rotating.

To add a gear, you left-click the left mouse button on a blank portion of the drawing panel near an existing gear, drag the mouse to an existing gear, and release the left mouse button.  A new gear may only be attached to one other gear.

When you add a new gear, a dialog will pop up asking you for the color of the new gear.  You can choose a color and left-click the "Yes" button to make the new gear that color.  If you left-click the "No" button, the gear will be black.

To remove a gear, you left-click the right mouse button on the exusting gear and release the right mouse button.  You may not remove the original gear.  To remove all the gears, except the origianl gear, left-click the right mouse button on a blank portion of the drawing panel.

The gear teeth don't always mesh together.  I couldn't figure out how to adjust the new gear to make the teeth mesh.  You can delete the new gear and try again if you want to improve the meshing.

Once you've placed all your gears, left-click on the "Start Gear Rotation" button at the bottom of the frame.  The original gear will rotate clockwise.  The other gears will rotate counterclockwise or clockwise, depending on which gear they are connected to.  You can stop the gear rotation by left-clicking on the "Stop Gear Rotation" button at the bottom of the frame.

Here's what the GUI looks like after I've added a few gears and rotated them.

![Additional Gears](readme-resources/geardemo2.png)
