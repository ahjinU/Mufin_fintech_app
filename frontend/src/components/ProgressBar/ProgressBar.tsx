interface ProgressBarProps {
    barGage: number;
  }
  
  export default function ProgressBar({ barGage }: ProgressBarProps) {
    return (
      <div className="w-full h-[1rem] rounded-[0.8rem] bg-custom-light-purple overflow-hidden">
        <div
          className="h-full rounded-[0.8rem] bg-custom-purple"
          style={{ width: `${barGage}%` }}
        ></div>
      </div>
    );
  }
  