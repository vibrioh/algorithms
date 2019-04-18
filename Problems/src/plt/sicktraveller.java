import java.util.*;

public class sicktraveller {
    enum Health {
        HEALTHY("HEALTHY"),
        SICK("SICK"),
        RECOVERING("RECOVERING");

        private final String status;

        Health(String status) {
            this.status = status;
        }
    }

    static class City{
      String name;
      Traveler [] travelers;
    }

    static class Traveler {
        String name;
        Health status;
        Health nextstatus;
        String[] schedule;
        int scheduleIterator;

        Traveler(String name, Health status, String[] travelSchedule) {
            this.name = name;
            this.status = status;
            this.schedule = travelSchedule;
            this.scheduleIterator = 0;
        }

        String getLocation() {
            return schedule[scheduleIterator];
        }

        void setNextState(boolean isExposed) {
            switch (status)
            {
              case HEALTHY:
                nextstatus = isExposed ? Health.SICK : Health.HEALTHY;
                break;
              case SICK:
                nextstatus = Health.RECOVERING;
                break;
              case RECOVERING:
                nextstatus = Health.HEALTHY;
                break;
            }
        }
    }

    static class TravelerManager {
        List<Traveler> travelers = new ArrayList<>();
        Map<String,List<Traveler>> cities = new HashMap<>();

        void addTraveler(Traveler traveler) {
            travelers.add(traveler);
        }

        void updateCities() {
          for(Traveler t : travelers)
          {
            if(!cities.containsKey(t.schedule[t.scheduleIterator]))
              cities.put(t.schedule[t.scheduleIterator],new ArrayList<>());
            List<Traveler> list = cities.get(t.schedule[t.scheduleIterator]);
            list.add(t);
            cities.put(t.schedule[t.scheduleIterator],list);
          }
        }

        void clearCities() {
          cities.clear();
        }

        void updateStates() {
            String location = "";
            boolean exposed = false;
            for(Traveler t : travelers)
            {
              location = t.getLocation();
              exposed = isExposed(location);
              t.setNextState(exposed);
            }
            for(Traveler t : travelers)
            {
              t.status = t.nextstatus;
              t.scheduleIterator = (t.scheduleIterator + 1) % t.schedule.length;
            }
        }

        boolean isExposed(String location) {
            List<Traveler> list = cities.get(location);
            for(Traveler t : list) {
              if(!t.status.equals(Health.HEALTHY))
                return true;
            }
            return false;
        }

        boolean isEveryoneHealthy() {
            for(Traveler t : travelers) {
                if(!t.status.equals(Health.HEALTHY))
                    return false;
            }
            return true;
        }

        public String getNames()
        {
          String s = "";
          for(Traveler t : travelers)
            s += t.name + " ";
          return s.trim();
        }
        public String getStatuses()
        {
          String s = "";
          for(Traveler t : travelers)
            s += t.status + " ";
          return s.trim();
        }
    }

    static String[] traceDiseases(String[] initialStates) {
        List<String> ans = new LinkedList<>();
        TravelerManager tm = new TravelerManager();
        for(String s : initialStates) {
            String[] info = s.split(" ");
            tm.addTraveler(
                    new Traveler(
                            info[0],
                            Health.valueOf(info[1]),
                            Arrays.copyOfRange(info, 2, info.length)
                    )
            );
        }
        ans.add(tm.getNames());
        while (!tm.isEveryoneHealthy() && ans.size() - 1 < 365) {
             tm.updateCities();
             ans.add(tm.getStatuses());
             tm.updateStates();
             tm.clearCities();
        }
        if(tm.isEveryoneHealthy())
             ans.add(tm.getStatuses());
        ans.add(String.valueOf(ans.size() - 1));
        return ans.toArray(new String[ans.size()]);
    }



    public static void main(String[] args) {

        String [] input2 = new String[4];
        input2[0] = "Wilson SICK Palo DC London Palo";
        input2[1] = "Yun HEALTHY Palo";
        input2[2] = "Ali RECOVERING DC DC DC London";
        input2[3] = "James HEALTHY London";

        String[] output = traceDiseases(input2);
        for(String s : output)
            System.out.println(s);
    }
}
