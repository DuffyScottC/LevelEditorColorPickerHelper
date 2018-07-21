using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Tilemaps;
#if UNITY_EDITOR
using UnityEditor;
#endif

/// <summary>
/// Strictly a helper class for the convenience of the developer. If you place
/// all your GameObjects/Tiles (by dragging and dropping them in large amounts)
/// into the array(s) of this script, then press the "Fill Entities" button,
/// they will be loaded into the MixedLevelGenerator script's entities. This way,
/// you don't have to drag and drop them individually. You can delete this script
/// after use.
/// 
/// It is a separate script on purpose, so that you can easily reset the
/// associated MixedLevelGenerator script without loosing your lists of
/// Tiles/GameObjects in the "MixedEntityFiller.tiles" and
/// "MixedEntityFiller.gameObjects" array.
/// </summary>
[RequireComponent(typeof(MixedLevelGenerator))]
public class MixedEntityFiller : MonoBehaviour {

    /// <summary>
    /// The Tiles that the user wants to load (alphabetically) into
    /// the Entity objects of the LevelGenerator script
    /// </summary>
    public Tile[] tiles;
    /// <summary>
    /// The GameObjects that the user wants to load (alphabetically) into
    /// the Entity objects of the LevelGenerator script
    /// </summary>
    public GameObject[] gameObjects;

    public void FillEntities() {
        //get a reference to the LevelGenerator component
        MixedLevelGenerator levelGenerator = GetComponent<MixedLevelGenerator>();
        //if we have a link to the LevelGenerator component
        if (levelGenerator != null) {
            //get the TEntities array
            TEntity[] tileEntities = levelGenerator.tileEntities;
            //loop through all the GameObjects the user put in this script
            for (int i = 0; i < tiles.Length; i++) {
                //if i is less than the length of the Entities array
                if (i < tileEntities.Length) {
                    //set the corosponding Entity's Tile to this Tile
                    tileEntities[i].tile = tiles[i];
                }
            }

            //get the GOEntities array
            GOEntity[] gameObjectEntities = levelGenerator.gameObjectEntities;
            //loop through all the GameObjects the user put in this script
            for (int i = 0; i < gameObjects.Length; i++) {
                //if i is less than the length of the Entities array
                if (i < gameObjectEntities.Length) {
                    //set the corosponding Entity's GameObject to this GameObject
                    gameObjectEntities[i].gameObject = gameObjects[i];
                }
            }
        } else {
            Debug.LogError("This GameObject has no LevelGenerator component.");
        }
    }
}

#if UNITY_EDITOR
/// <summary>
/// Creates a button at the bottom of the script that allows users to
/// quickly and easily fill in the Tiles/GameObjects of their array of
/// TEntity/GOEntity objects. A user may drag and drop all of their
/// Tiles/GameObjects into the "MixedEntityFiller.tiles" and
/// "MixedEntityFiller.gameObjects"arrays (in alphabetical order). 
/// Then the user may press the "Fill GameObjects" button and the
/// Tile objects will be sent instantly to the
/// "MixedLevelGenerator.tileEntities" and 
/// "MixedLevelGenerator.gameObjectEntities" arrays.
/// </summary>
[CustomEditor(typeof(MixedEntityFiller))]
class MixedEntityFillerEditor : Editor {

    public override void OnInspectorGUI() {
        base.OnInspectorGUI();
        //get the script with the tiles
        MixedEntityFiller myScript = (MixedEntityFiller)target;
        //if the user pressed the "Fill Entities" button
        if (GUILayout.Button("Fill Entities")) {
            //fill the Entities
            myScript.FillEntities();
        }
    }
}
#endif
