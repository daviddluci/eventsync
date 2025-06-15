import { useState } from "react";
import { useToast } from "../hooks/use-toast"

export const CreateEvent = () => {
  const { toast } = useToast();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState({
    eventName: "",
    description: "",
  });

const handleSubmit = async (e) => {
  e.preventDefault();
  setIsSubmitting(true);
setTimeout(() => {
  
}, 1000);
  try {
    const response = await fetch("https://eventsync-final.onrender.com/events", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: formData.eventName,
        description: formData.description,
      }),

    });

    if (response.status === 200) {
      console.log("ok");
      toast({
        title: "Request sent!",
        description: "Feedback has been successfully submitted.",
        duration: 3500,
      });
    } else {
      console.log("fault");
      alert("Oops! Something went wrong...")
    }

    setFormData({ eventName: "", description: "" });
  } catch (error) {
    alert("Error connecting to EventSyncAPI.")
    console.error("error");
  }
  setIsSubmitting(false);
};

  return (
    <section
      id="createEvent"
      className="py-24 bg-secondary/30 min-h-screen flex items-center justify-center"
    >
      <div className="container mx-auto max-w-[500px]">
        <h2 className="text-3xl md:text-4xl font-bold mb-4 text-center">
          Create new <span className="text-primary"> Event</span>
        </h2>
        <form className="space-y-6" onSubmit={handleSubmit}>
          <div className="relative">
            <input
              type="text"
              id="eventName"
              name="eventName"
              required
              value={formData.eventName}
              className="w-full px-4 py-3 rounded-md border border-input bg-background focus:outline-hidden focus:ring-2 focus:ring-primary resize-none"
              placeholder="Title..."
              onChange={(e) =>
                setFormData({ ...formData, eventName: e.target.value })
              }
            />
          </div>

          <div className="relative">
            <textarea
              id="description"
              name="description"
              required
              rows={5}
              value={formData.description}
              className="w-full px-4 py-3 rounded-md border border-input bg-background focus:outline-hidden focus:ring-2 focus:ring-primary resize-none"
              placeholder="Event description..."
              onChange={(e) =>
                setFormData({ ...formData, description: e.target.value })
              }
            />
          </div>
          <button
            type="submit"
            disabled={isSubmitting}
            className="cosmic-button w-full flex items-center justify-center gap-2"
          >
            {isSubmitting ? "Creating.." : "Create"}
          </button>
        </form>
      </div>
    </section>
  );
};
