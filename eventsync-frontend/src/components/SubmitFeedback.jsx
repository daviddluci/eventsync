import { useState } from "react";
import { useToast } from "../hooks/use-toast"

export const SubmitFeedback = () => {
  const { toast } = useToast();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState({
    id: "",
    text: "",
  });

const handleSubmit = async (e) => {
  e.preventDefault();
  setIsSubmitting(true);
  try {
    const response = await fetch(`https://eventsync-latest.onrender.com/events/${formData.id}/feedback`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        text: formData.text,
      }),

    });

    if (response.status === 200) {
      console.log("ok");
      toast({
        title: "Request sent!",
        description: "Feedback has been successfully submitted.",
        duration: 3500,
      });
    } else if (response.status === 404) {
      console.log("notfound"); 
      alert(`Event with ID: ${formData.id} is not found.`) 
    } else {
      console.log("fault");
      alert("Oops! Something went wrong...")
    }
    setIsSubmitting(false);
    setFormData({ id: "", text: "" });
  } catch (error) {
      alert("Error connecting to EventSyncAPI.")
      console.error("error");
  }
};

  return (
    <section
      id="submitFeedback"
      className="py-24 bg-secondary/30 min-h-screen flex items-center justify-center"
    >
      <div className="container mx-auto max-w-[500px]">
        <h2 className="text-3xl md:text-4xl font-bold mb-4 text-center">
          Submit <span className="text-primary"> Feedback</span>
        </h2>
        <form className="space-y-6" onSubmit={handleSubmit}>
          <div className="relative">
            <input
              type="number"
              id="id"
              name="id"
              required
              value={formData.id}
              className="w-full px-4 py-3 rounded-md border border-input bg-background focus:outline-hidden focus:ring-2 focus:ring-primary resize-none                         appearance-none 
                        [&::-webkit-inner-spin-button]:appearance-none 
                        [&::-webkit-outer-spin-button]:appearance-none"
              placeholder="Enter event ID..."
              onChange={(e) =>
                setFormData({ ...formData, id: e.target.value })
              }
            />
          </div>

          <div className="relative">
            <textarea
              id="text"
              name="text"
              required
              rows={5}
              value={formData.text}
              maxLength={255}
              className="w-full px-4 py-3 rounded-md border border-input bg-background focus:outline-hidden focus:ring-2 focus:ring-primary resize-none"
              placeholder="Feedback for the event..."
              onChange={(e) =>
                setFormData({ ...formData, text: e.target.value })
              }
            />
          </div>

          <button
            type="submit"
            disabled={isSubmitting}
            className="cosmic-button w-full flex items-center justify-center gap-2"
          >
            {isSubmitting ? "Submitting.." : "Submit"}
          </button>
        </form>
      </div>
    </section>
  );
};
