FasdUAS 1.101.10   ��   ��    k             x     �� ����    4    �� 
�� 
frmk  m     	 	 � 
 
  F o u n d a t i o n��        x    �� ����    2   ��
�� 
osax��        l     ��������  ��  ��        l     ��  ��    ? 9 Takes a hexadecimal color code as an argument and pastes     �   r   T a k e s   a   h e x a d e c i m a l   c o l o r   c o d e   a s   a n   a r g u m e n t   a n d   p a s t e s      l     ��  ��    1 + it into Aseprite's color picker instantly.     �   V   i t   i n t o   A s e p r i t e ' s   c o l o r   p i c k e r   i n s t a n t l y .   ��  i        I     �� ��
�� .aevtoappnull  �   � ****  o      ���� 0 argv  ��    k     M        l     �� ! "��   ! C = Get the user's current mouse position so that you can return    " � # # z   G e t   t h e   u s e r ' s   c u r r e n t   m o u s e   p o s i t i o n   s o   t h a t   y o u   c a n   r e t u r n    $ % $ l     �� & '��   & - ' the mouse to this position at the end.    ' � ( ( N   t h e   m o u s e   t o   t h i s   p o s i t i o n   a t   t h e   e n d . %  ) * ) r     	 + , + n     - . - I    �������� 0 mouselocation mouseLocation��  ��   . n     / 0 / o    ���� 0 nsevent NSEvent 0 m     ��
�� misccura , o      ���� 0 mouseloc mouseLoc *  1 2 1 l  
  3 4 5 3 r   
  6 7 6 n   
  8 9 8 o    ���� 0 x   9 o   
 ���� 0 mouseloc mouseLoc 7 o      ���� 0 xc xC 4  get mouse x-coord    5 � : : " g e t   m o u s e   x - c o o r d 2  ; < ; l    = > ? = r     @ A @ n     B C B o    ���� 0 y   C o    ���� 0 mouseloc mouseLoc A o      ���� 0 yc yC >  get mouse y-coord    ? � D D " g e t   m o u s e   y - c o o r d <  E F E l   ��������  ��  ��   F  G H G l   �� I J��   I - ' Convert the x-coordinate to an integer    J � K K N   C o n v e r t   t h e   x - c o o r d i n a t e   t o   a n   i n t e g e r H  L M L r     N O N l    P���� P c     Q R Q o    ���� 0 xc xC R m    ��
�� 
long��  ��   O o      ���� 0 xcoord xCoord M  S T S l   ��������  ��  ��   T  U V U l   �� W X��   W - ' Convert the y-coordinate to an integer    X � Y Y N   C o n v e r t   t h e   y - c o o r d i n a t e   t o   a n   i n t e g e r V  Z [ Z r    ! \ ] \ l    ^���� ^ c     _ ` _ o    ���� 0 yc yC ` m    ��
�� 
long��  ��   ] o      ���� 0 tempy tempY [  a b a l  " "�� c d��   c B < cliclick's x and y are opposite to NSEvent's, so we need to    d � e e x   c l i c l i c k ' s   x   a n d   y   a r e   o p p o s i t e   t o   N S E v e n t ' s ,   s o   w e   n e e d   t o b  f g f l  " "�� h i��   h 8 2 subtract the value from the height of the screen.    i � j j d   s u b t r a c t   t h e   v a l u e   f r o m   t h e   h e i g h t   o f   t h e   s c r e e n . g  k l k r   " ' m n m l  " % o���� o \   " % p q p m   " #����8 q o   # $���� 0 tempy tempY��  ��   n o      ���� 0 ycoord yCoord l  r s r l  ( (��������  ��  ��   s  t u t l  ( (�� v w��   v = 7 open the aseprite color picker and click the hex field    w � x x n   o p e n   t h e   a s e p r i t e   c o l o r   p i c k e r   a n d   c l i c k   t h e   h e x   f i e l d u  y z y I  ( -�� {��
�� .sysoexecTEXT���     TEXT { m   ( ) | | � } } j / u s r / l o c a l / b i n / c l i c l i c k   c : 4 3 5 , 8 8 0   c : 4 3 5 , 8 8 0   c : 7 0 0 , 7 0 0��   z  ~  ~ O   . = � � � k   2 < � �  � � � l  2 2�� � ���   � * $ type the argument (the hex code) in    � � � � H   t y p e   t h e   a r g u m e n t   ( t h e   h e x   c o d e )   i n �  � � � l  2 2�� � ���   � 0 * note: this will throw error without input    � � � � T   n o t e :   t h i s   w i l l   t h r o w   e r r o r   w i t h o u t   i n p u t �  ��� � I  2 <�� ���
�� .prcskprsnull���     ctxt � l  2 8 ����� � n   2 8 � � � 4   3 8�� �
�� 
cobj � m   6 7����  � o   2 3���� 0 argv  ��  ��  ��  ��   � m   . / � ��                                                                                  sevs  alis    �  Macintosh HD               �@��H+  p�System Events.app                                              s����        ����  	                CoreServices    �@��      ��1    p�p�p�  =Macintosh HD:System: Library: CoreServices: System Events.app   $  S y s t e m   E v e n t s . a p p    M a c i n t o s h   H D  -System/Library/CoreServices/System Events.app   / ��     � � � l  > >�� � ���   � > 8 press enter to accept the color and return the mouse to    � � � � p   p r e s s   e n t e r   t o   a c c e p t   t h e   c o l o r   a n d   r e t u r n   t h e   m o u s e   t o �  � � � l  > >�� � ���   � #  the user's original position    � � � � :   t h e   u s e r ' s   o r i g i n a l   p o s i t i o n �  ��� � I  > M�� ���
�� .sysoexecTEXT���     TEXT � b   > I � � � b   > G � � � b   > C � � � m   > A � � � � � F / u s r / l o c a l / b i n / c l i c l i c k   k p : e n t e r   m : � o   A B���� 0 xcoord xCoord � m   C F � � � � �  , � o   G H���� 0 ycoord yCoord��  ��  ��       �� � � ���   � ����
�� 
pimr
�� .aevtoappnull  �   � **** � �� ���  �   � � � �� ���
�� 
cobj �  � �   �� 	
�� 
frmk��   � �� ���
�� 
cobj �  � �   ��
�� 
osax��   � �� ���� � ���
�� .aevtoappnull  �   � ****�� 0 argv  ��   � ���� 0 argv   � �������������������������� |�� ����� � �
�� misccura�� 0 nsevent NSEvent�� 0 mouselocation mouseLocation�� 0 mouseloc mouseLoc�� 0 x  �� 0 xc xC�� 0 y  �� 0 yc yC
�� 
long�� 0 xcoord xCoord�� 0 tempy tempY��8�� 0 ycoord yCoord
�� .sysoexecTEXT���     TEXT
�� 
cobj
�� .prcskprsnull���     ctxt�� N��,j+ E�O��,E�O��,E�O��&E�O��&E�O��E�O�j O� �a k/j UOa �%a %�%j ascr  ��ޭ