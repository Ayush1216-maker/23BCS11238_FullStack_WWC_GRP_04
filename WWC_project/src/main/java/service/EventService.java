package service;
import model.Event;
import java.util.ArrayList;
import java.util.List;

public class EventService {
 private List<Event> events=new ArrayList<>();
  public List<Event> getAllEvents(){
      return events;
  }
  public Event getEvenById(int id){
      return events.stream()
              .filter(e->e.getId()==id)
              .findFirst().orElse(null);
  }
  public Event addEvent(Event event){
      events.add(event);
      return event;
  }
  public boolean deleteEvent(int id){
      return events.removeIf(e->e.getId()==id);

  }
}
