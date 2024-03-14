import { InformationCircleIcon } from '@heroicons/react/24/solid';

interface GuideTextProps {
  text: string;
}

export default function GuideText({ text, ...props }: GuideTextProps) {
  return (
    <p className="flex gap-[0.5rem]" {...props}>
      <InformationCircleIcon className="w-[2rem] h-[2rem] text-custom-purple" />
      <span className="custom-light-text text-custom-black leading-[2.2rem]">
        {text}
      </span>
    </p>
  );
}
