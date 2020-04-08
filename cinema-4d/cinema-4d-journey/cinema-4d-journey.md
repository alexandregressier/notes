#  Cinema 4D Journey, Motion Design School

## 0

A combo box

Middle mouse button : show projection views3

Attrviutes Manager is the most used window is Cinema 4d

Move tool has properties as well

Tag is some additional properties or a list of attributes bound to an object
The Display Tag

Almost everything around us consist of primitives

Control drag works for almost everything in Cinema 4D

Null can be transformed by ERT
Consider them as Photoshop's groups

Null objects should contain objects that have been created at the origin

Vertices is the correct word for vertexes

Polygons are parametric objects craeted in Cinema 4D

Making editable is a destructive operation: you lose the ability to edit properties
You can then use:
- Polygon (Face) mode
- Edge mode
- Point (Vertex) mode
Press RET to cycle through them

When in polygon mode:
- Right click to show every operation available

SPC to go back to Live Selection

Pivot editing mode: gizmo editing mode = very useful
-> Analog to anchor editing mode in AE

Extrude inner

To create a material, double click anywhere in the manager

A material is another tag

Go to polygon mode to paint on a per-face basis

Maxon removed its logo since r21 in the bottom left corner

UL: loop selection tool

Multiple material tags are applied when appliying multiple materials to different polygons (+ selection tags)

Spline are not rendered

You can long click an icon instead of dragging it

A floor object is an infinite surface

By default, lights do not cast shadow in cinema 4d
-> Shadow: Area

Disable shadow cast:
- Add a compositing tag (allows changing render properties of an object)
- Cast Shadows

Renders:
- C-R
- S-R
- C-B

Output rendering have presets

Right mouse button on object: Show list of modeling commands

C-S-F4: close all projects


## 1.0

Select the Standard layout instead of the Startup one

Customize > Save as Startup Layout

Object Manager
Attribute Manager: edit properties

`N B`: Change display mode to Gouraud Shading (Lines)

Balance the minimum number of with the shape of th epolygon you want

Add a bevel: Caps > Fillet [t]
Alt + Click on number input to make it more precise

Control + Arrow of Gizmo = Copy

Click anywhere when on ERT to act on the 3 axis

You can box select in the Object Manager

Fillet is the chanfrein

When creating a new object, select one and press alt to create it at the same position

A _spline_ is a flat object

A good practice is to copy an object and paste it in a new one to isolate it

Chose the right plane rather than rotating an object

Cap > Round and Fillet relate to the same notion

Don't forget to remove the fillet cap when needed

The Boole generator enable you to susbtract shapes
- Create single object
- Hide new edges

Alt-G: create null

IMPORTANT: putting an object as a child set its coordinates locally, which you can reset to 0

Use vertex mode to select anchor points of splines

Make a rectangle editable to edit its points w/ C

Set a first point and uncheck Close spline to be able to delete vertices

b spline is like the alternative pen in illustrator

S-S: enable snap
REMEMBER: you can shift select tabs

Lathe

Select all points w/ C-A
Chanfer

Viewport Solo Single is Cinema4D isolated mode

Disolve to delete an edge (C-DEL)

Filet caps can be added to editable objects by adding a bevel
transformer

When editing render settings, make sure to select RedShift from the dropdown

Protection is in Rigging Tags (used to be in Cinema 3D Tags)

Easiest way to get full light setup is to use an HDR
- wide dynamic range
- Commonly spherical

Use brute force for both of the GI engines

It is called albedo in physics


## 1.1

Cinema 4D default renderer is not as good as other solutions like:
- Corona render
- Arnold
- V-Ray
- RedShift

PBR: pyshically based rendering
PBR is not fully implemented yet
(e.g., new PBR material)

W/o PBR, light were made directly on the object by drawing gradients for example
- No energy conservation

There are multiple independent step done when rendering (light, color...)

PBR light is better
- It is not a point but a surface

Falloff:
- Choose None if you want your light to be a sun
- Choose Inverse Square if you want your light to be close to your objects

Specular is the old method for calculating lights

In real life, a 100% reflective surface does not exist according to funnel law

IRL, objects do not have a 100% smooth edge, which enables them to reflect light

Sampling is what you want if you want to reduce noise

In C-B, you can add effects at the bottom w/ the Effect... button

Oxides can have dilectric (instead of conductive)

Subsurface scattering is like when you put your fingers behind a source of light

PET is a plastic material (the one of the bottles)

Search Results
Web results

Polyethylene terephthalate 

Refraction indexes:
- Water 1.33
- Glass 1.51

Luminance can be reflected as well

Luminance can produce noise: use Polygon Light in the illumination tab


## 2.0

L - Enable Axis
Press U then Y - Grow selection
Press U then K - Shrink selection
Press U then W - Select all elements
Press U then L - Loop selection mode
Press K then L - Loop Pass Cut
Press M then E - Polygon Pen
I - Extrude Inner (in Polygons Mode)

Polygonal modeling: describing the shape of objects w/ the help of polygons

Spheres are not mathematically correct in 3D modeling softwares b/c they only contain a large number of polygons

Toppology: the way polygons describe the shape of an object
-> Example a default sphere VS a isocahedron (both spheres represented differently)

Good & bad topology:
-> A vast question, but balance between the minimum number of elements required and the accurate representation of your shape
-> Good topology is crucial when deforming objects

Triangle topology are not very good

Widespread opinion: the best topology is when using quads (4 sided polygons)
-> Better for smoothing

N-gons (bad) VS quads (good)

At times, topology dictates its way of creating a model
-> Do not alwayrs require quads

IMPORTANT: Subdivision surface is the modifier you use to smooth out

Live selection has the option "Only Select Visible Elements"
-> Always enable it by default

Use Middle Mouse Button and drag to scale the size of the brsh

Tolerant selection: outline the whole polygon is no longer required

<X> <Y> <Z>: toggle x y z axis

Each of ERT has two modes:
1. Tweak mode: unselect everything and drag elements one by one
2. Classic mode: select one element at a time

L: enable axis

You can choose another mode while having a selection on one mode and pass it onto another

Shift: add to selection

Extrude: enables to create new geometry by moving a polygon or several polygons in a given direction

Extrude inner: same as extrude but does not move the polygon

You can select multiple polygons and apply operations (eg extrude)

Bevel: extrude + inner extrude

W: world

Faces are not always plane

You can add subdivisions to your bevel

Extrude can also be applied to edges

MS: bevel

Cut tool: add geometry by adding edges
- Line CUt
- Plane Cut
- Loop path cut: kl
  - Click + multiple times
  - Click ||| to recenter

Polygon Pen (ME) is really useful
- Press modifiers to play w/ it

1. Subdivide
  - Click th Gear
  - 2 + Smooth Subdivision
2. Select all the bottom edges at set them at y=0
3. Make a cross and bevel
4. Clear up ngons with ME
5. Select the points and iron them

Cilynders are good candidates for 

IMPORTANT: Create single object

It's clear that their count must be a multiple of six

IMPORTANT: set y=0 to FLATTEN

Inner extrude is good to solve ngons

Bevel your edges

Control to remove edges w/ polypen

I: extrude inner

Extrude inner can enable you to smoothen edges

W/ modeling, practice is the key to good skill
+ Theory of modeling is very simple
-> Analogy w/ drawing

Applying a bevel at each point is fundamental to make them into squares

Shift + V – Configure
Hold Alt (Option) + Left mouse button click (on slider) – Fraction values on slider
Enter – Switch between selection modes
Esc – Undo a PolyPen action
Q – Turn on/off generator of geometry (e.g. Subdivision Surface)


## 3.0

Beverage can

These photos must be as orthogonal as possible

You can drag photos in the viewport of orthogonal views

S-V: edit photos in the Back tab

Symetry generator is useful
-> You can work on both sides

Inner bevel is great to draw inner shapes

Dissolve is what you expect delete to do

only discs should not be quads

Press C when you want to delte your symetry (and delete the null)

Apply bevel between two loops

Q: toggle subdivision surface

KL: kut loop

Bevel > Kut Loop

Alternative to extrusion:
- Edge loop
- Control + gizmo down

Scale + Control = New geometry on the sides3

Shit+Alt = rotate the camera back where it was

9/0 to toggle box selection/live

V: great like blender

IMPORTANT: press SPC in a field to set it to 0

Use Cloth Surface to add a thickness to a plan

You can achive quads in a pizza situation by taking two slices

extruding a pologon loop is good


# 4.0

Press U then F — Fill selection mode
Press U then W — Select Connected (polygons, edges, etc.)

UV is needed to understand how to apply a flat texture to an object

You can double click any point to set its coordinates

UV is an altertive set of coordinates to xyz which is stored in the geometry

The checkerboard tag denotes uv coordinates mapping

UV coordinates define the position of a polygon on a plane on a texture

Raster pictures VS vector pictures

W coordinate is rarely used

UV are sufficient to describe a polygon postion on a surface

UV coordinates can split polygons in uv space

At the same time, these polygons remain merged in the 3D space

Textures can be of any size and of any aspect rate

It assumed that the height and width are equal to 1

(.5,.5 is in the center)

Parametric objects already have texture coordinates (generated together w/ geometry)

You can change the projection a texture by clicking its material tag

Window > BodyBpaint 3D > New Texture View...

A texture will start repeating itself if it goes out of the square
(unckeck Tile if undesired)

The coordinates of other projection types are calculated on the go

Best to use texture mode
- You can use all the transformation tool in it (ERT)


Cubic: the texture is projected on 6 sides
Frontal: the texture is project orthogonaly

Camera mapping mode is one of most used in 33d graphics
-> Texture are only projected where the camera is filming


C4D module is quite old
UV tools are part of BodyPaint in C4D (old module)
BodyPaint is quite old

UV unwrapping: unwrapping a 3d object into 2d
-> You split the faces of an object onto 2d space in order to pain it

Right Click on any tag > Generate UV coordinate

Select the edges for which there will be a cut in uv space

Problems:
- Any cut will later be a texture seen, which is a problem in itself
- Some of the details cannnot be perfectly placed on a flat surface (will be distorded a bit)

You need to find the balance between the number of cuts and the quality of detail

The ring it scut in 3 pieces

UM: Path selection tool
(You can go back in your selection)

Save your selection:
Select > Set selection
-> Creates a tag which you can double click

Reselect and set again to overwrite the tag

You can rename your Selection tags

Layout > Bodypaint UV Edit

Select the UV special selection mode:
- UV points
- UV polygons

Change project in the Projection tab

Shrink mode never has cuts in it
-> Select it

In Relax UV, you can drag your selection tag (and check the two boxes)

Press apply then

Optimal Mapping > Realign

/UV islands/

GIZMO SHOWING = SOMETHING SELECTED

Begin to select polygon
UW: select the whole piece
Select > Hide Selected

If you are in edge mode, and change to polygon mode, you will not loose your selection in edge mode

Steps:
- Select the edges where you want to cut
- Set selection
- Name it "UV Cut"
- BP UV Edit layout
- Projection > Shrink (= make sure you do not have any cuts)
- Relax UV: ensure that uv cut is selected (+ the above checkbox + autoralign) > Apply 
-> MAKE SURE THAT NO CHECKBOX ABOVE IS CHECKED (LIKE Pin Border Points)
-> Apply is not deterministic

Note that the cyclinder texture is not precise, you can use the cylinder projection

Select UV polygons & Select at least a polygon from each of them > UW
Optimal Mapping > realign > apply (several times)
-> Increase the spacin gif you want

Select the can w/ UW > Projection > cylinder
UV Commands > Fit UV to Canvas

Model mode

subdivision surface works correctly by default

Select the most accurate UV setting for subdivision surface

We'll use RedShift later

Make 2 loops of selection:
UF: select all the polygons between the two loops

A polygon selection tag will be created when you apply a texture to polygons
-> The material has a selection parameter

When loading a texture, go to Editor > No Scaling

Summary:
UV describe the way textures are going to be applied on a 3D object


## 5.0

A packshot is a still or moving image of a product, usually including its packaging and labeling, used to portray the product's reputation in advertising or other media

- Model a simple environment
- Create an animation rig
- Animate the can
- Light sources & Material
- Render

For TV, FPS is 25 or 30 depending on the country

Cinema: usually 24

C-D: Project settings

C4D will scale the timeline if you change the FPS
-> Also change the FPS value in Render Settings

Set FPS to 24

Switch off the images in orthogonal views w/ S-V > Back

Alt-G for nulls b/c it acts like *group*

IMPORTANT: Create a null and place the pivot at the bottom (S-S to snap)
-> Then make it a parent and set its height to 0

A cyclorama

Active and Red/Green Lights allow to act on parented objects or not

GO BACK TO MODEL MODE TO SET THE PIVOT
-> Allows to repair the cyclorama in case of errors

Add a Bevel Deformer to the studio
Increase the number of subdivisions + turn on Angle to 

Layers:
- Double click the panel to make a layer
- Drag objects into it or vice versa
(Press control to make all the children into the layer)

V: visibility in viewpoer
R: render
L: lock layer (instead of using protection tag)
M: hide from object manager

Animating:
- The can should be placed steadily on the floor
- Set a point (a null) where the can will be leaning on when reeling

Root
--Left
----Right
------Can

Animating two nulls is not easy
It is easier to animate one
-> Use XPresso

Create a null
Tag it XPresso
Double click to activate it

XPresso is a node programming language
- Drag the Tilt Control in the XPresso Editor
- Blue: IN, Red: OUT
- You can drag attributes by clicking their labels

Clamp: set min and max (in the attribute editor)
min is in radians for angle

Control drag to copy a node

Summary:
XPresso is useful to constrain attributes values that are meant to be animated

Left click an icon to create a keyframe

Make sure to drop the compensation null when the can has stopped moving

Range Mapper
Check reverse: * (-1)

S-F3: open timeline
Select F-Curve (the second icon)

Box select to select only one point
The flatter the curve, the slower the animation will be

H: maximize the display of the curve in the window

Can root can be transformed w/o changing the animation

It is a good practice to isolate each attribute into a single null
-> Use fewer keyframes w/in a complex animation

You can combine materials from different renderers
You can overwrite material by dragging them on the older material tag
-> Preserves the previous tag "Tag" attributes

Or:
Right click a material > Select Material Tags/Objects
Then move the material to the corresponding parameter

Redshift Dome Light is what you generally want
-> Use HDR image as a picture

The Shader Graph ressembles XPresso

Drag and drop a texture

textures.com

Out Color > Blue of RS Material > Base Properties/Reflection > Reflection Roughness

The texture has range that is unsuitable for our shader
-> Add a Change Range node (same as mapper node in XPresso)

Increase Scale

Small details make the render photo realistic

Material in computer graphics can be devided into 2 types:
- Dilectric (isolant)
- Conductive
-> Metals, and they have one particularity: they can call the reflections in their own color

The white paint of Coca-Cola is dilectric as well

There are several ways to control reflection in redshift:
We will use Reflection > Fresnel Type > Metalness (typical for Computer Graphics)

Set reflectivity to grey 30%
Roughness to 0.3 (make it more glossy)

Assign the texture output to color diffuse
0 on the letters, and 1 on the red parts of the can

Inverse the range by setting 1 to min and 0 to max

S-V to raise the opacity of the frame better

Dolly camera is a moving camera

H in the Render View of RS does not fit to screen (it flips the image horizontally)

Studio illumination = RS Area light
Similar light sources are used in phot studios

In RS render settings, set both GI engines to Brute force

You can apply a cloner on an object by changing Linear to object
You can put a mask selection on it

Change instance mode to Render instance

Push apart effector to achieve this
MoGraph > Effector > Push Apart
Spaces the drops
Scale apart scales down overlapping drops

In RS render settings, choose Frame Range to All Frames

Set the File in Path

You can set Samples Min & Max in Redshift to have a better result (but longer render time)

Enable Motion Blur as well

