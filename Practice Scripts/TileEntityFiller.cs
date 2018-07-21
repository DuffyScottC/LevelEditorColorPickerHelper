using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Tilemaps;
#if UNITY_EDITOR
using UnityEditor;
#endif

/// <summary>
/// Strictly a helper class for the convenience of the developer. If you place
/// all your Tiles (by dragging and dropping them in large amounts)
/// into the array(s) of this script, then press the "Fill Entities" button,
/// they will be loaded into the LevelGenerator script's entities. This way,
/// you don't have to drag and drop them individually. You can delete this
/// script after use. 
/// 
/// It is a separate script on purpose, so that you can easily reset the
/// associated TileLevelGenerator script without loosing your lists of
/// Tiles in the "TileEntityFiller.tiles" array.
/// </summary>
[RequireComponent(typeof(TileLevelGenerator))]
public class TileEntityFiller : MonoBehaviour {

    /// <summary>
    /// The tiles that the user wants to load (alphabetically) into
    /// the TileEntity objects of the TileLevelGenerator script
    /// </summary>
    public Tile[] tiles;

    public void FillEntities() {
        //Get a reference to the TileLevelGenerator component
        TileLevelGenerator tileLevelGenerator = GetComponent<TileLevelGenerator>();
        //if we have a link to the TileLevelGenerator component
        if (tileLevelGenerator != null) {
            //get the TileEntities array
            TileEntity[] tileEntities = tileLevelGenerator.tileEntities;
            //loop through all the Tile objects the user put in this script
            for (int i = 0; i < tiles.Length; i++) {
                //if i is less than the length of the TileEntities array
                if (i < tileEntities.Length) {
                    //set the corosponding TileEntity's tile to this tile
                    tileEntities[i].tile = tiles[i];
                }
            }
        } else {
            Debug.LogError("This GameObject has no TileLevelGenerator component.");
        }
    }

}

#if UNITY_EDITOR
/// <summary>
/// Creates a button at the bottom of the script that allows users to
/// quickly and easily fill in the Tile objects of their array of
/// TileEntity objects. A user may drag and drop all of their
/// Tile objects into the "TileEntityFiller.tiles" array (in
/// alphabetical order). Then the user may press the "Fill
/// Entities" button and the Tile objects will be sent
/// instantly to the "TileLevelGenerator.tileEntities" array.
/// </summary>
[CustomEditor(typeof(TileEntityFiller))]
class TileEntityFillerEditor : Editor {

    public override void OnInspectorGUI() {
        base.OnInspectorGUI();
        //get the script with the tiles
        TileEntityFiller myScript = (TileEntityFiller)target;
        //if the user pressed the "Fill Entities" button
        if (GUILayout.Button("Fill Entities")) {
            //fill the Entities
            myScript.FillEntities();
        }
    }
}
#endif
