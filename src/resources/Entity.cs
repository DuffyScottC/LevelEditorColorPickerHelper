using UnityEngine;

[System.Serializable]
public class Entity
{
    
	public Color color;
	public GameObject prefab;
	public Vector2 offset;

	public Entity(Color color) {
		this.color = color;
		this.offset = Vector2.zero;
	}

	public Entity(Color color, Vector2 offset) {
        this.color = color;
        this.offset = offset;
    }

}
