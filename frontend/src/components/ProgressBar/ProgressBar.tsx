interface ProgressBarProps {
  barGage: number;
  height: string;
}

export default function ProgressBar({ barGage, height }: ProgressBarProps) {
  return (
    <div
      className={`w-full ${height} rounded-[2rem] bg-custom-light-purple overflow-hidden`}
    >
      <div
        className="h-full rounded-[0rem] bg-custom-purple"
        style={{ width: `${barGage}%` }}
      ></div>
    </div>
  );
}
