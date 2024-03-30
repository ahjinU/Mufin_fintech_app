interface ProgressBarProps {
  barGage: number;
  height?: string;
  boundary?: string;
}

export default function ProgressBar({
  barGage,
  height = 'h-[1rem]',
  boundary = 'rounded-[2rem]',
}: ProgressBarProps) {
  return (
    <div
      className={`w-full ${height} rounded-[2rem] bg-custom-light-purple overflow-hidden`}
    >
      <div
        className={`h-full ${boundary} bg-custom-purple`}
        style={{ width: `${barGage}%` }}
      ></div>
    </div>
  );
}
