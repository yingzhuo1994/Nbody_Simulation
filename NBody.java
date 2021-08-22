public class NBody {
    // int N; // N represents the number of planets
    // double R; // R represents the radius of the universe
    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Planet[] planets = new Planet[N];
        double R = in.readDouble();

        int index = 0;
        while (index < N) {
            double xxPos = in.readDouble(), yyPos = in.readDouble();
            double xxVel = in.readDouble(), yyVel = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[index++] = new Planet(xxPos, yyPos, xxVel, yyVel, m, img);
        }

        return planets;
    }

    public static void main(String[] args) {
        double time = 0;
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);

        int N = planets.length;
        StdDraw.enableDoubleBuffering();
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);

        while (time < T) {
            // create xForces and yForces arrays
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            // calculate the net x and y forces for each body, store them respectively
            for (int i = 0; i < N; i++) {
                Planet curBody = planets[i];
                xForces[i] = curBody.calcNetForceExertedByX(planets);
                yForces[i] = curBody.calcNetForceExertedByY(planets);
            }

            // update each body's position, velocity and acceleration
            for (int i = 0; i < N; i++) planets[i].update(dt, xForces[i], yForces[i]);

            // Draw the background image.
            StdDraw.picture(0, 0, "images/starfield.jpg", radius * 2, radius * 2);

            // Draw all of the Bodies
            for (Planet body : planets) body.draw();

            // show the offscreen buffer
            StdDraw.show();

            // pause the animation for 10 ms
            StdDraw.pause(10);

            // increase time by dt
            time += dt;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
