import { useState } from "react";
import { FeedbackCircle } from "./FeedbackCircle ";
import { useToast } from "../hooks/use-toast"

const myData = [
  { name: "Positive", value: 10 },
  { name: "Neutral", value: 20 },
  { name: "Negative", value: 5 },
];

export const EventBreakdown = () => {
  const { toast } = useToast();
  const [event, setEvent] = useState();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState({
    id: "",
  })

  const handleGet = async(e) => {
    e.preventDefault();
    setIsSubmitting(true);
    try {
        const response = await fetch(`https://eventsync-final.onrender.com/events/${formData.id}/summary`, {
        method: "GET"
        });

        if (response.status === 200){
            const data = await response.json();
            setEvent(data);
            toast({
            title: "Request sent!",
            description: "Event has been refreshed successfully.",
            duration: 2500,
            });
        } else if (response.status === 404) {
            console.log("notfound"); 
            alert(`Event with ID: ${formData.id} is not found.`) 
        } else {
            console.log("fault");
            alert("Oops! Something went wrong...")
        }
    } catch (error) {
        alert("Error connecting to EventSyncAPI.")
        console.error("error");
    }
    setIsSubmitting(false);
  }

  return (
    <section
    id="eventBreakdown"
    className="py-24 bg-secondary/30 min-h-screen flex items-center justify-center"
    >
    <div className="container mx-auto max-w-5xl">
        <h2 className="text-3xl md:text-4xl font-bold mb-12 text-center">
        Event <span className="text-primary"> breakdown</span>
        </h2>

        <div className="flex gap-4 items-center justify-center mb-8">
        <form onSubmit={handleGet} className="flex gap-4 items-center justify-center mb-8">
            <input
                type="number"
                id="id"
                name="id"
                placeholder="Enter event ID..."
                required
                value={formData.id}
                className="w-40 px-3 py-2 rounded-md border border-input bg-background 
                        focus:outline-none focus:ring-2 focus:ring-primary 
                        appearance-none 
                        [&::-webkit-inner-spin-button]:appearance-none 
                        [&::-webkit-outer-spin-button]:appearance-none"
                onChange={(e) => setFormData({ ...formData, id: e.target.value})}
            />
            <button type="submit" className="cosmic-button" disabled={isSubmitting}>
                {isSubmitting ? "Retrieving.." : "Get Event"}
            </button>
        </form>
        </div>

      <div className="container mx-auto max-w-[700px]">
          <div className="bg-card p-6 rounded-lg shadow-xs card-hover">
            <div className="flex items-center justify-center mb-4 relative">
              <span className="absolute left-0 w-8 h-8 flex items-center justify-center border-2 border-primary rounded-full text-sm font-bold opacity-60">{event?.id || "ID"}</span>
              <span className="font-bold text-lg text-center max-w-xs mx-auto">{event?.title || "No event selected"}</span>
            </div>
            <div className="text-left mb-4">
              <h3 className="font-semibold text-base break-words">{event?.description || "No event selected"}</h3>
            </div>
            <span className="font-bold text-primary text-lg text-center max-w-xs mx-auto">Sentiment Analysis</span>
            <div className="flex justify-center">
                <FeedbackCircle
                data={[
                { name: "Positive", value: event?.feedbackSentimentStats?.positiveCount || 0 },
                    { name: "Neutral", value: event?.feedbackSentimentStats?.neutralCount || 0 },
                    { name: "Negative", value: event?.feedbackSentimentStats?.negativeCount || 0 },
                    ]}
                />
            </div>
            <div className="text-center mb-4 py-3">
                <h3 className="font-semibold text-base">
                    Feedbacks gathered:{" "}
                    {event
                    ? (
                        (event.feedbackSentimentStats?.positiveCount || 0) +
                        (event.feedbackSentimentStats?.neutralCount || 0) +
                        (event.feedbackSentimentStats?.negativeCount || 0) +
                        (event.feedbackSentimentStats?.unrecognizedCount || 0)
                        )
                    : "No event selected"
                    }
                </h3>
            </div>
          </div>
        </div>

    </div>
    </section>
  );
};