import React from "react";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";

const COLORS = ["#4CAF50", "#FFC107", "#F44336"];

export const FeedbackCircle = ({ data = [
  { name: "Positive", value: 10 },
  { name: "Neutral", value: 10 },
  { name: "Negative", value: 10 },
] }) => (
  <PieChart width={300} height={300}>
    <Pie
      data={data}
      cx="50%"
      cy="50%"
      innerRadius={70}
      outerRadius={100}
      paddingAngle={9}
      dataKey="value"
      stroke="none"
      label
    >
      {data.map((entry, index) => (
        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
      ))}
    </Pie>
    <Tooltip />
    <Legend />
  </PieChart>
);
