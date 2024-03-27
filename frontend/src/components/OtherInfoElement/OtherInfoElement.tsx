import Image from 'next/image';

interface OtherInfoElementProps {
  imageSrc?: string | null;
  leftExplainText: string;
  leftHighlightText: string;
  state: 'UP' | 'DOWN';
  rightExplainText?: string;
  rightHighlightText?: string;
  money?: string;
}

export default function OtherInfoElement({
  imageSrc,
  leftExplainText,
  leftHighlightText,
  rightExplainText,
  rightHighlightText,
  state,
  money,
}: OtherInfoElementProps) {
  return (
    <section className="w-full flex justify-between items-center">
      <div className="w-full flex gap-[1rem] items-center">
        {imageSrc && (
          <Image
            src={imageSrc}
            width={42}
            height={42}
            alt={leftExplainText}
            className="w-[4.2rem] h-[4.2rem]"
          />
        )}
        <div className="flex flex-col justify-between">
          <span className="custom-semibold-text">{leftHighlightText}</span>
          <span className="custom-medium-text">{leftExplainText}</span>
        </div>
      </div>

      {money ? (
        <span className="min-w-fit custom-semibold-text text-custom-purple">
          {state === 'UP' ? '+ ' + money : '- ' + money}
        </span>
      ) : (
        <div className="min-w-fit flex flex-col justify-between items-end">
          <span className="custom-semibold-text">{rightHighlightText}</span>
          <span
            className={`custom-medium-text ${
              state === 'UP' ? 'text-custom-red' : 'text-custom-blue'
            }`}
          >
            {state === 'UP' ? '+' + rightExplainText : rightExplainText}
          </span>
        </div>
      )}
    </section>
  );
}
