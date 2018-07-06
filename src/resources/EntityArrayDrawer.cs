#if UNITY_EDITOR
using UnityEngine;
using UnityEditor;

/// <summary>
/// The PropertyDrawer for arrays of Entities labeled with 
/// EntityAttribute
/// </summary>
//tell the drawer that it is drawing of EntityAttribute types
[CustomPropertyDrawer(typeof(EntityArrayAttribute))]
public class EntityArrayDrawer : PropertyDrawer {
	/// <summary>
    /// The space between vertical fields in the inspector
    /// </summary>
	private readonly float space = 3f;

    /// <summary>
	/// The number of Entity attributes that are to be displayed vertically
	/// in the EditorGUI property. 
    /// </summary>
	private readonly float numOfProps = 2f;

	public override void OnGUI(Rect position, SerializedProperty property, 
	                           GUIContent label) {
		try {
			//get the attribute (of type NamedArrayAttribute)
			EntityArrayAttribute myAt = (EntityArrayAttribute)attribute;
            //get the index of the current element of the entities array
			int i = int.Parse(property.propertyPath.Split('[', ']')[1]);

            //begin the property for this entity
            EditorGUI.BeginProperty(position, label, property);

			//draw the label for this property using the passed in name
			position = EditorGUI.PrefixLabel(position, 
			                                 GUIUtility.GetControlID(FocusType.Passive), 
			                                 new GUIContent(myAt.names[i]));

            //save the default indent
            var indent = EditorGUI.indentLevel;
			//remove the indent for the child fields
            EditorGUI.indentLevel = 0;

            //form the rects for each entity attribute
			var prefabRect = new Rect(position.x, 
			                          position.y + space, 
			                          position.width, 
			                          position.height / numOfProps - space);
			var offsetRect = new Rect(position.x, 
			                         position.y + position.height / numOfProps + space, 
			                         position.width / 2 - space, 
			                         position.height / numOfProps - space);
			var colorRect = new Rect(position.x + position.width / 2 + space,
                                     position.y + position.height / numOfProps + space,
                                     position.width / 2 - space,
                                     position.height / numOfProps - space);

            //get the properties of the current entity object
			var prefabProperty = property.FindPropertyRelative("prefab");
            var offsetProperty = property.FindPropertyRelative("offset");
			var colorProperty = property.FindPropertyRelative("color");

            /*
             * Draw fields for the properties of the current entity object
             * GUIContent.none means the field will not be labeled
             */
			EditorGUI.PropertyField(prefabRect, prefabProperty, GUIContent.none);
            EditorGUI.PropertyField(offsetRect, offsetProperty, GUIContent.none);
			EditorGUI.PropertyField(colorRect, colorProperty, GUIContent.none);
			         
            //reset the indent to the default, saved above
            EditorGUI.indentLevel = indent;
            //end the property for this entity
			EditorGUI.EndProperty();
		} catch {
			//if, for any reason, something horrible goes wrong, use the default
			base.OnGUI(position, property, label);
		}
	}

	public override float GetPropertyHeight(SerializedProperty property, 
	                                        GUIContent label) {
		/*
		 * The height of an entity property. This is the default height
		 * of a property, plus the space between each property, multiplied
		 * by the number of properties in an Entity object.
		 */
		return (base.GetPropertyHeight(property, label) + space) * numOfProps;
	}
}
#endif
