using UnityEngine;

[System.Serializable]
public class Entity
{
    
  public Color color;
  public GameObject prefab;

  public Entity(Color color)
  {
    this.color = color;
  }

}