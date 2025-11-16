# Issue

I'm writing code in javaFx, which has a gridpane that has multiple cell's of different color. One of the pane has a Imageview as child.

When i set the setOnMouseDragged on the imageview and start dragging the image, the cell's that the image crosses over on are surprisingly replaced by the parent of the imageView.

but why is that happening? it should not replace the new node with parent node yet keep's doing that? how to solve this?



To better understand my problem the have Minimum reproducible code here https://github.com/crazycatMyopic/issue3/tree/master

 Once you click the Hello button a board is made with a ball the the bottom right corner

click on the block get it's id's.

once you get the id's. move the ball over that block, and suddenly the id's are changed to the ball's parent.

I have also checked e.getGestureSource()==pane on setOnMouseDragOver for the cell node's. So on non-parent node it should give false   but it gives true .Can anyone explain why this is happening? and how do i fix it?



## Run

```
 mvn javafx:run
```
