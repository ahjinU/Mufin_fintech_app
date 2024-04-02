import { InformationCircleIcon } from '@heroicons/react/24/solid';

interface GuideTextProps {
  text: React.ReactNode | string;
}

export default function GuideText({ text, ...props }: GuideTextProps) {
  return (
    <div
      className="flex gap-[0.5rem] items-center custom-light-text
       text-custom-black"
      {...props}
    >
      <InformationCircleIcon className="w-[2rem] h-[2rem] text-custom-purple" />
      <span className="mb-[0.1rem]">{text}</span>
    </div>
  );
}
