public class Planet{
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet
    private static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
          this.xxPos = xP;
          this.yyPos = yP;
          this.xxVel = xV;
          this.yyVel = yV;
          this.mass = m;
          this.imgFileName = img;
        }

    public Planet(Planet p){
         // xxPos = p.xxPos;
         // yyPos = p.yyPos;
         // xxVel = p.xxVel;
         // yyVel = p.yyVel;
         // mass = p.mass;
         // imgFileName = p.imgFileName;
         /** Below can replaces all above */
         this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p){
         double dx;
         double dy;
         double r;
         dx = p.xxPos - this.xxPos;
         dy = p.yyPos - this.yyPos;
         r = Math.sqrt(dx * dx + dy * dy);
         return r;
    }

    public double calcForceExertedBy(Planet p){
         double r;
         double F;
         r = this.calcDistance(p);
         F = G * this.mass * p.mass / (r * r);
         return F;
    }

    public double calcForceExertedByX(Planet p){
         double dx;
         double r;
         double F;
         double Fx;
         dx = p.xxPos - this.xxPos;
         r = this.calcDistance(p);
         F = this.calcForceExertedBy(p);
         Fx = F * dx / r;
         return Fx;
    }

    public double calcForceExertedByY(Planet p){
         double dy;
         double r;
         double F;
         double Fy;
         dy = p.yyPos - this.yyPos;
         r = this.calcDistance(p);
         F = this.calcForceExertedBy(p);
         Fy = F * dy / r;
         return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
         double netFx = 0;
         for (Planet p : allPlanets) {
           if (!this.equals(p)) {
               netFx += this.calcForceExertedByX(p);
           }
         }
         return netFx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
         double netFy = 0;
         for (Planet p : allPlanets) {
           if (!this.equals(p)) {
               netFy += this.calcForceExertedByY(p);
           }
         }
         return netFy;
    }

    public void update(double dt, double fX, double fY){
         double aXX;
         double aYY;
         aXX = fX / this.mass;
         aYY = fY / this.mass;
         this.xxVel += aXX * dt;
         this.yyVel += aYY * dt;
         this.xxPos += xxVel * dt;
         this.yyPos += yyVel * dt;
    }

    public void draw() {
		    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
