using System;
using System.Runtime.CompilerServices;

// Panda p1 = new Panda ("test1");
// Panda p2 = new Panda ("test2");

// Console.WriteLine (p1.Name);
// Console.WriteLine (p2.Name);

// Console.WriteLine (Panda.Population);

// public class Panda
// {
//     public string Name; // instance field
//     public static int Population; // static field

//     public Panda (string n)
//     {
//         Name = n;
//         Population = Population + 1; // incremement static field
//     }
// }


// class Program
// {
//     static void Main()
//     {
//         int x = 12345;
//         long y = x; // implicit conversoin
//         short z = (short)x; // explicit conversion
//     }
// }

// value vs reference types

// value:
// Point p1 = new Point();
// p1.X = 7;

// Point p2 = p1; // assignment causes copy

// Console.WriteLine(p1.X); // 7
// Console.WriteLine(p2.X); // 7

// p1.X = 9;

// Console.WriteLine(p1.X); // 9
// Console.WriteLine(p2.X); // 7

// value types have independent storage

// reference type has two parts, the object and reference to the object

// reference:

// Point p1 = new Point();
// p1.X = 7;
// Point p2 = p1; // copies p1 reference

// Console.WriteLine(p1.X); // 7
// Console.WriteLine(p2.X); // 7

// p1.X = 9;

// Console.WriteLine(p1.X); // 9
// Console.WriteLine(p2.X); // 9



// public struct Point { public int X, Y; } // custom value type

// public class Point { public int X, Y; } // custom reference type