import { StarBackground } from "../components/StarBackground";
import { ThemeToggle } from "../components/ThemeToggle";
import { Navbar } from "../components/Navbar";
import { Welcome } from "../components/Welcome";
import { CreateEvent } from "../components/CreateEvent";
import { EventsList } from "../components/EventsList";
import { SubmitFeedback } from "../components/SubmitFeedback";
import { FeedbackCircle  } from "../components/FeedbackCircle ";
import { EventBreakdown } from "../components/EventBreakdown";
import { Footer } from "../components/Footer";

export const Home = () => {
    return <div className="min-h-screen bg-background text-foreground overflow-x-hidden">
        <ThemeToggle />
        <StarBackground />
        <Navbar />
        <main>
            <Welcome />
            <CreateEvent />
            <EventsList />
            <SubmitFeedback />
            <EventBreakdown />
        </main>
        <Footer />
    </div>
};