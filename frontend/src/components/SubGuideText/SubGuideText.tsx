import { ExclamationTriangleIcon } from '@heroicons/react/24/solid';

interface SubGuideTextProps {
  text: string;
}

export default function SubGuideText({ text, ...props }: SubGuideTextProps) {
  return (
    <p className="flex gap-[0.5rem]" {...props}>
      <ExclamationTriangleIcon className="w-[2rem] h-[2rem] text-custom-light-purple" />
      <span className="custom-light-text text-custom-light-purple">{text}</span>
    </p>
  );
}
