import React, { useEffect, useState } from "react";
import { useToast } from "../hooks/use-toast"

const events = [

];

export const EventsList = () => {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const { toast } = useToast();
  const [events, setEvents] = useState([]);

  const fetchEvents = async () => {
  try {
    const response = await fetch("https://eventsync-latest.onrender.com/events", {
      method: "GET"
    });

    if (response.status === 200) {
      const data = await response.json();
      setEvents(data);
      toast({
        title: "Request sent!",
        description: "Events have been refreshed successfully.",
        duration: 2500,
      });
    } else {
      console.log("fault");
      alert("Oops! Something went wrong fetching events...")
    }

  } catch (error) {
      alert("Error connecting to EventSyncAPI.")
      console.error("error");
    }
  }

  useEffect(() => {
    fetchEvents();
  }, []);

  const handleRefresh = (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setTimeout(() => {
      fetchEvents();
      setIsSubmitting(false);
    }, 1000);
  }
  
  return (
    <section id="listEvents" className="py-24 px-7 relative bg-secondary/30">
      <div className="container mx-auto max-w-5xl">
        <h2 className="text-3xl md:text-4xl font-bold mb-12 text-center">
          All <span className="text-primary"> events</span>
        </h2>
        <button onClick={handleRefresh} className="cosmic-button mb-8 inline-block" disabled={isSubmitting}>
          {isSubmitting ? "Refreshing.." : "Refresh Events"}
        </button>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-15 px-30">
        {events.map((event, key) => (
          <div key={key} className="bg-card p-6 rounded-lg shadow-xs card-hover">
            <div className="flex items-center justify-center mb-4 relative">
              <span className="absolute left-0 w-8 h-8 flex items-center justify-center border-2 border-primary rounded-full text-sm font-bold opacity-60">{event.id}</span>
              <span className="font-bold text-lg text-center max-w-xs mx-auto">{event.title}</span>
            </div>
            <div className="text-left mb-4">
              <h3 className="font-semibold text-base break-words"> {event.description}</h3>
            </div>
          </div>
          ))}
        </div>
    </section>
  );
}
